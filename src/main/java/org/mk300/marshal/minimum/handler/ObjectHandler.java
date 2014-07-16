/*
 * Copyright 2014 Masazumi Kobayashi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mk300.marshal.minimum.handler;


import java.io.IOException;
import java.lang.reflect.Field;

import org.mk300.marshal.common.ClassMetaDataRegistry;
import org.mk300.marshal.minimum.MarshalHandler;
import org.mk300.marshal.minimum.io.BAInputStream;
import org.mk300.marshal.minimum.io.BAOutputStream;
import org.mk300.marshal.minimum.io.NaturalNumberIoHelper;
import org.mk300.marshal.minimum.io.OInputStream;
import org.mk300.marshal.minimum.io.OOutputStream;


/**
 * 
 * @author mkobayas@redhat.com
 *
 */
public final class ObjectHandler implements MarshalHandler<Object> {

	/**
	 * 循環参照があった場合に、無限ループに陥るのを防止する為のカウンタの上限
	 */
	private final static int maxNestLimit = 100;
	
	/**
	 * 循環参照があった場合に、無限ループに陥るのを防止する為のカウンタ
	 */
	private final static ThreadLocal<Integer> nestCount = new ThreadLocal<Integer>() { 
		@Override
		protected Integer initialValue() {
            return 0;
		}
	};
	
	@Override
	public final void writeObject(OOutputStream out, Object o) throws IOException {

		Field currentProcessfeild = null; // エラー時のメッセージ出力用。

		assert o != null;
		
		try {
			BAOutputStream baos = null;
			OOutputStream out_tmp = null;
			int skipPositionForBAO = -1;
			
			if( out.isUnderlayBAOut() ) {
				out_tmp = out;
			} else {
				baos = new BAOutputStream();
				out_tmp = new OOutputStream(baos);
			}
			
			Integer n = nestCount.get();
			if(n > maxNestLimit) {
				throw new IOException("無限ループです。 data=" + o);
			}
			
			nestCount.set(n+1);
			
			Class<?> oClazz = o.getClass();
						
			Field[] fieldList = ClassMetaDataRegistry.getFieldList(oClazz);
			
			if( out.isUnderlayBAOut() ) {
				skipPositionForBAO = out_tmp.skipIntSize();
			}
			
			// 項目数を書込（Beanクラスに項目数変更があった場合、読み込み側で処理を打ち切る為に利用される)
			NaturalNumberIoHelper.writeNaturalNumber(out_tmp, fieldList.length);
			
			for(Field f : fieldList) {
				currentProcessfeild = f;
				
				Class<?> c = f.getType();
				
				if( c == String.class) {
					String s = (String)f.get(o);
					out_tmp.writeString(s);
				} else if(c.isPrimitive()) {
					if(c == Integer.TYPE){
						out_tmp.writeInt(f.getInt(o));
						
					} else if(c == Long.TYPE) {
						out_tmp.writeLong(f.getLong(o));
						
					} else if(c == Short.TYPE) {
						out_tmp.writeShort(f.getShort(o));
						
					} else if(c == Boolean.TYPE) {
						out_tmp.writeBoolean(f.getBoolean(o));
						
					} else if(c == Double.TYPE) {
						out_tmp.writeDouble(f.getDouble(o));
						
					} else if(c == Float.TYPE) {
						out_tmp.writeFloat(f.getFloat(o));
						
					} else if(c == Character.TYPE) {
						out_tmp.writeChar(f.getChar(o));
						
					}  else if(c == Byte.TYPE) {
						out_tmp.writeByte(f.getByte(o));
						
					} 
					
				} else {
					Object o2 = f.get(o);
					out_tmp.writeObject(o2);
				}
			}
			
			if( out.isUnderlayBAOut() ) {
				int writeSize = out.size() - skipPositionForBAO -4;
				out.writeIntDirect(writeSize, skipPositionForBAO);
			} else {
				out_tmp.flush();
				out.writeInt(baos.size());
				baos.writeTo(out);
			}
			
		} catch (Exception e) {
			throw new RuntimeException("バイナリ書き込みで例外発生 data=" + o + ", feild=" + currentProcessfeild ,e);
		} finally {
			Integer n = nestCount.get();
			nestCount.set(n-1);
		}
		
		
	}

	// 項目数が多い新しいバージョンのクラス（項目追加は最後尾）でマーシャルされたバイナリを、項目数が少ない古いバージョンのクラスで読み込めるようにする対応。
	// バイナリとクラスの項目数不一致の場合は、以下に従う。
	// 
	// ・ バイナリの項目数より、クラスの項目数の方が多い場合。 (古いクラスで書込み、新しいクラスで読込みを想定）
	//   バイナリを先頭から読み込み、fieldListの順番でオブジェクトにセットする。バイナリのデータが無くなった時点で、処理を打ち切る。
	//   従って、fieldListの最後の方は値が設定されていない状態になる（Object型の場合はnull、プリミティブ型の場合は初期値)
	// 
	// ・ バイナリの項目数より、クラスの項目数の方が少ない場合。(新しいクラスで書込み、古いクラスで読込みを想定)
	//   バイナリを先頭から読み込み、fieldListの順番でオブジェクトにセットする。 fieldListの全ての項目が処理された時点で処理を打ち切る。
	//   従って、バイナリの最後のほうが切り捨てられる。
	
	@Override
	public final Object readObject(OInputStream in, Class<Object> clazz) {
		Field currentProcessfeild = null; // エラー時のメッセージ出力用。
		
		try {

			Object data = clazz.newInstance();

			int binarySize = in.readInt();

			
			BAInputStream bais;
			if( in.isUnderlayBAIn() ) {
				BAInputStream underlay = in.getUnderlayBAIn();
				bais = new BAInputStream(underlay.getBuf(), underlay.getPos(), binarySize);
				in.skip(binarySize);
			} else {
				byte[] binary = new byte[binarySize];
				in.readFully(binary);
				bais = new BAInputStream(binary);
			}
			OInputStream ois = new OInputStream(bais);
			
			
			// Beanクラスに項目追加があった場合、読み込み側で処理を打ち切る為に利用される
			int fieldCount = NaturalNumberIoHelper.readNaturalNumber(ois);
			int processedCount = 0;
			
			Field[] fieldList = ClassMetaDataRegistry.getFieldList(clazz);
			
			for(Field f : fieldList) {
				currentProcessfeild = f;
				
				processedCount += 1;
				if( processedCount > fieldCount) {
					// 現在のBeanのプロパティの数が、書き込まれたプロパティのサイズを超えている。これはクラスに項目追加があった場合。
					return data;
				}
				
				Class<?> c = f.getType();

				if(c == String.class) {
					String s = ois.readString();
					f.set(data, s);
				} else if(c.isPrimitive()) {

					if(c == Integer.TYPE){
						Integer i = ois.readInt();
						f.setInt(data, i);
					} else if(c == Long.TYPE) {
						Long l = ois.readLong();
						f.setLong(data, l);
					} else if(c == Short.TYPE) {
						Short s = ois.readShort();
						f.setShort(data, s);
					} else if(c == Boolean.TYPE) {
						Boolean b = ois.readBoolean();
						f.setBoolean(data, b);
					} else if(c == Double.TYPE) {
						Double d = ois.readDouble();
						f.setDouble(data, d);
					} else if(c == Float.TYPE) {
						Float flo = ois.readFloat();
						f.setFloat(data, flo);
					} else if(c == Character.TYPE) {
						Character l = ois.readChar();
						f.setChar(data, l);
					} else if(c == Byte.TYPE) {
						Byte l = ois.readByte();
						f.setByte(data, l);
					}
					
				} else {
					Object o = ois.readObject();
					f.set(data, o);
				}
			}
			
			return data;
		} catch (Exception e) {
			throw new RuntimeException("バイナリ読み込みで例外発生 data=" + clazz + ", feild=" + currentProcessfeild ,e);
		}
		
	}
	
}
