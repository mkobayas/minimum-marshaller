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

package org.mk300.marshal.externalize;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

import org.mk300.marshal.common.ClassMetaDataRegistry;

/**
 * {@link AutoExternalizable}と{@link java.io.Externalizable}が指定されたクラスを
 * 自動的にread/writeするためのユーティリティクラス。<br>
 * 本クラスは、{@link java.io.Externalizable#writeExternal(ObjectOutput)}、{@link java.io.Externalizable#readExternal(ObjectInput)}
 * 内で利用する。<br>
 * 循環参照を持つオブジェクトグラフには利用できない。<br>
 * N対1参照を持つオブジェクトグラフは、復元時にN対Nになる<br>
 * 
 * <p>使用例<br>
 * <pre> <code>
 * import com.redhat.example.common.util.AutoExternalizable;
 * import com.redhat.example.common.util.ExternOrder;
 * import com.redhat.example.common.util.ExternalizableUtil;
 * 
 * &#x40;AutoExternalizable
 * public class Hoge implements Externalizable {
 *     private static final long serialVersionUID = 1L;
 *     
 *     // properties
 *     &#x40;ExternOrder(1)  // optional
 *     private List<CheckResultDetailDataBom> checkResultDetailDataBomList;
 *     
 *     &#x40;ExternOrder(2)  // optional
 *     private List<ApplFrmInfoBom> applFrmInfoBomList;
 *     
 *     &#x40;ExternOrder(3)  // optional
 *     private String groupId;
 *     
 *     &#x40;ExternOrder(4)  // optional
 *     private String validationTypeCde;
 * 
 *     // getter/setter
 * 
 *     &#x40;Override
 *     public void writeExternal(ObjectOutput out) throws IOException {
 *         ExternalizableUtil.write(out, this);
 *     }
 * 
 *     &#x40;Override
 *     public void readExternal(ObjectInput in) throws IOException {
 *         ExternalizableUtil.read(in, this);
 *     }
 * }
 * </code>
 * </pre>
 * </p>
 * 
 * @author mkobayas@redhat.com
 *
 */
public class ExternalizableUtil {

	/**
	 * 循環参照があった場合に、無限ループに陥るのを防止する為のカウンタの上限
	 */
	public static int maxNestLimit = 100;
	
	/**
	 * 循環参照があった場合に、無限ループに陥るのを防止する為のカウンタ
	 */
	private final static ThreadLocal<Integer> nestCount = new ThreadLocal<Integer>() { 
		@Override
		protected Integer initialValue() {
            return 0;
		}
	};
	
	/**
	 * {@link Class#isAnnotationPresent(Class)}が、意外と重い処理だったので、キャッシュする。
	 */
	private static ConcurrentHashMap<Class<?>, Boolean> isAutoExternalizablePresentCache = new ConcurrentHashMap<Class<?>, Boolean>();
	
	public static void write(ObjectOutput out, Object data) throws IOException {
		Field currentProcessfeild = null; // エラー時のメッセージ出力用。

		try {
			Integer n = nestCount.get();
			if(n > maxNestLimit) {
				throw new IOException("無限ループです。 data=" + data);
			}
			
			nestCount.set(n+1);

			if(data != null && !(isAutoExternalizablePresent(data.getClass()))) {
				throw new IOException("@AutoExternalizableが指定されていないクラスでExternalizableUtilが呼ばれました。dataCalss=" + data.getClass());
			}
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream out_tmp = new ObjectOutputStream(baos);
			
			
			Field[] fieldList = ClassMetaDataRegistry.getFieldList(data.getClass());

			// 項目数を書込（Beanクラスに項目数変更があった場合、読み込み側で処理を打ち切る為に利用される)
			out_tmp.writeInt(fieldList.length);
			
			// フィールド単位に、データ書込み
			for(Field f : fieldList) {
				currentProcessfeild = f;
				
				Class<?> c = f.getType();
				
				if( c == String.class) {
					String s = (String)f.get(data);
					if(s != null) {
						// DataOutputのwriteUTFはUTF-8エンコード後のバイトサイズが65535以下でなければいけない
						//  UTF-8は、1-3バイトの可変なので、全て3バイトと想定して閾値を20000文字とする。
						//  なお、s.length()の戻り値は、サローゲートペアの場合、１文字でも2を返却するので
						//  サローゲートペア文字でも大丈夫
						if(s.length() < 20000) {
							out_tmp.writeByte(1);
							out_tmp.writeUTF(s);
						} else {
							byte[] strBytes = s.getBytes("UTF-8");
							out_tmp.writeByte(2);
							out_tmp.writeInt(strBytes.length);
							out_tmp.write(strBytes);
						}
					} else {
						out_tmp.writeByte(0);
					}
					
				} else if(c.isPrimitive()) {
					if(c == Integer.class || c == int.class){
						out_tmp.writeInt(f.getInt(data));
						
					} else if(c == Long.class || c == long.class) {
						out_tmp.writeLong(f.getLong(data));
						
					} else if(c == Short.class || c == short.class) {
						out_tmp.writeShort(f.getShort(data));
						
					} else if(c == Boolean.class || c == boolean.class) {
						out_tmp.writeBoolean(f.getBoolean(data));
						
					} else if(c == Double.class || c == double.class) {
						out_tmp.writeDouble(f.getDouble(data));
						
					} else if(c == Float.class || c == float.class) {
						out_tmp.writeFloat(f.getFloat(data));
						
					} else if(c == Character.class || c == char.class) {
						out_tmp.writeChar(f.getChar(data));
						
					} else if(c == Byte.class || c == byte.class) {
						out_tmp.writeByte(f.getByte(data));
						
					} 
					
				} else {
					Object o = f.get(data);
					if( isAutoExternalizablePresent(c) ) {
						if(o != null) {
							out_tmp.writeByte(1); // 新しいオブジェクトを書き込むマーカとして1バイト利用(オブジェクトが有る場合、1)
							write(out_tmp, o); // 再帰的に書込
						} else {
							out_tmp.writeByte(0); // 新しいオブジェクトを書き込むマーカとして1バイト利用(オブジェクトがnullの場合、0)
						}
					} else {
						if(o != null) {
							out_tmp.writeByte(1);
							out_tmp.writeObject(o);
						} else {
							out_tmp.writeByte(0);
						}
					}
				}
			}
			
			out_tmp.flush();
			out.writeInt(baos.size());
			out.write(baos.toByteArray());
			
		} catch (Exception e) {
			throw new IOException("バイナリ書き込みで例外発生 data=" + data + ", feild=" + currentProcessfeild ,e);
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
	
	public static void read(ObjectInput in, Object data) throws IOException {
		Field currentProcessfeild = null; // エラー時のメッセージ出力用。
		
		try {

			int binarySize = in.readInt();

			byte[] binary = new byte[binarySize];
			in.readFully(binary);
			ByteArrayInputStream bais = new ByteArrayInputStream(binary);
			ObjectInputStream ois = new ObjectInputStream(bais);
			
			// Beanクラスに項目追加があった場合、読み込み側で処理を打ち切る為に利用される
			int fieldCount = ois.readInt();
			int processedCount = 0;
			
			Field[] fieldList = ClassMetaDataRegistry.getFieldList(data.getClass());
			
			for(Field f : fieldList) {
				currentProcessfeild = f;
				processedCount += 1;
				if( processedCount > fieldCount) {
					// 現在のBeanのプロパティの数が、書き込まれたプロパティのサイズを超えている。これはクラスに項目追加があった場合。
					return;
				}
				
				Class<?> c = f.getType();

				if(c == String.class) {
					byte marker = ois.readByte();
					if(marker == 1) {
						String s = ois.readUTF();
						f.set(data, s);
					} else if(marker == 2){
						int size = ois.readInt();
						byte[] strBytes = new byte[size];
						ois.readFully(strBytes);
						String s = new String(strBytes, "UTF-8");
						f.set(data, s);
					}
				} else if(c.isPrimitive()) {
					if(c == Integer.class || c == int.class ){
						Integer i = ois.readInt();
						f.setInt(data, i);
					} else if(c == Long.class || c == long.class) {
						Long l = ois.readLong();
						f.setLong(data, l);
					} else if(c == Short.class || c == short.class) {
						Short s = ois.readShort();
						f.setShort(data, s);
					} else if(c == Boolean.class || c == boolean.class) {
						Boolean b = ois.readBoolean();
						f.setBoolean(data, b);
					} else if(c == Double.class || c == double.class) {
						Double d = ois.readDouble();
						f.setDouble(data, d);
					} else if(c == Float.class || c == float.class) {
						Float flo = ois.readFloat();
						f.setFloat(data, flo);
					} else if(c == Character.class || c == char.class) {
						Character l = ois.readChar();
						f.setChar(data, l);
					} else if(c == Byte.class || c == byte.class) {
						Byte b = ois.readByte();
						f.setByte(data, b);
					}
				} else {
					if( isAutoExternalizablePresent(c) ) {
						if(ois.readByte() == 1) {
							Object o = c.newInstance();
							read(ois, o);
							f.set(data, o);
						}
					} else {
						if(ois.readByte() == 1) {
							Object o = ois.readObject();
							f.set(data, o);
						}
					}
				}
			}
		} catch (Exception e) {
			throw new IOException("バイナリ読み込みで例外発生 data=" + data.getClass() + ", feild=" + currentProcessfeild ,e);
		}
		
	}
	
	private static boolean isAutoExternalizablePresent(Class<?> clazz) {
		Boolean ret = isAutoExternalizablePresentCache.get(clazz);
		if(ret == null) {
			ret = Boolean.valueOf(clazz.isAnnotationPresent(AutoExternalizable.class));
			isAutoExternalizablePresentCache.put(clazz, ret);
		}
		return ret;
	}
	
	
}