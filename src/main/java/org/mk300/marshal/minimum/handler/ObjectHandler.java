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

import org.mk300.marshal.common.MarshalException;
import org.mk300.marshal.common.TheUnsafe;
import org.mk300.marshal.common.UnsafeClassMetaDataRegistry;
import org.mk300.marshal.common.UnsafeFieldAccessor;
import org.mk300.marshal.minimum.MarshalHandler;
import org.mk300.marshal.minimum.io.NaturalNumberIoHelper;
import org.mk300.marshal.minimum.io.OInput;
import org.mk300.marshal.minimum.io.OOutput;


/**
 * 
 * @author mkobayas@redhat.com
 *
 */
@SuppressWarnings("restriction")
public final class ObjectHandler implements MarshalHandler<Object> {
	
	@Override
	public final void writeObject(OOutput out, Object o) throws IOException {

		UnsafeFieldAccessor currentProcessfeild = null; // エラー時のメッセージ出力用。

		assert o != null;
		
		try {
			int skipPositionForBAO = -1;
			
			Class<?> oClazz = o.getClass();
						
			UnsafeFieldAccessor[] fieldList = UnsafeClassMetaDataRegistry.getFieldList(oClazz);
			
			skipPositionForBAO = out.skipIntSize();
			
			// 項目数を書込（Beanクラスに項目数変更があった場合、読み込み側で処理を打ち切る為に利用される)
			NaturalNumberIoHelper.writeNaturalNumber(out, fieldList.length);
			
			for(UnsafeFieldAccessor f : fieldList) {
				currentProcessfeild = f;
				
				Class<?> c = f.getType();
				
				if( c == String.class) {
					String s = (String)f.get(o);
					out.writeString(s);
				} else if(c.isPrimitive()) {
					if(c == Integer.TYPE){
						out.writeInt(f.getInt(o));
						
					} else if(c == Long.TYPE) {
						out.writeLong(f.getLong(o));
						
					} else if(c == Short.TYPE) {
						out.writeShort(f.getShort(o));
						
					} else if(c == Boolean.TYPE) {
						out.writeBoolean(f.getBoolean(o));
						
					} else if(c == Double.TYPE) {
						out.writeDouble(f.getDouble(o));
						
					} else if(c == Float.TYPE) {
						out.writeFloat(f.getFloat(o));
						
					} else if(c == Character.TYPE) {
						out.writeChar(f.getChar(o));
						
					}  else if(c == Byte.TYPE) {
						out.writeByte(f.getByte(o));
						
					} 
					
				} else {
					Object o2 = f.get(o);
					out.writeObject(o2);
				}
			}
			
			int writeSize = out.size() - skipPositionForBAO -4;
			out.writeIntDirect(writeSize, skipPositionForBAO);

		
		} catch (MarshalException e) {
			throw e;
		} catch (Exception e) {
			throw new MarshalException("バイナリ書き込みで例外発生 data=" + o + ", feild=" + currentProcessfeild ,e);
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
	public final Object readObject(OInput in, Class<Object> clazz) throws IOException {
		UnsafeFieldAccessor currentProcessfeild = null; // エラー時のメッセージ出力用。
		
		try {

			Object data = TheUnsafe.unsafe.allocateInstance(clazz);
			
			int binarySize = in.readInt();
			
//			BAInputStream bais;
//			if( in.isUnderlayBAIn() ) {
//				BAInputStream underlay = in.getUnderlayBAIn();
//				bais = new BAInputStream(underlay.getBuf(), underlay.getPos(), binarySize);
//				in.skip(binarySize);
//			} else {
//				byte[] binary = new byte[binarySize];
//				in.readFully(binary);
//				bais = new BAInputStream(binary);
//			}

			// 項目数が少ない場合に対応するため、OIputStreamをブランチする。
			OInput ois = in.branch();
			in.skip(binarySize);
			
			// Beanクラスに項目追加があった場合、読み込み側で処理を打ち切る為に利用される
			int fieldCount = NaturalNumberIoHelper.readNaturalNumber(ois);
			int processedCount = 0;
			
			UnsafeFieldAccessor[] fieldList = UnsafeClassMetaDataRegistry.getFieldList(clazz);
			
			for(UnsafeFieldAccessor f : fieldList) {
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
		} catch (MarshalException e) {
			throw e;
		} catch (Exception e) {
			throw new MarshalException("バイナリ読み込みで例外発生 data=" + clazz + ", feild=" + currentProcessfeild ,e);
		}
		
	}
	
}
