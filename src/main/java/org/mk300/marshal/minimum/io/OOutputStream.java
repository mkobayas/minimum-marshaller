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

package org.mk300.marshal.minimum.io;


import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.mk300.marshal.common.InfiniteLoopException;
import org.mk300.marshal.minimum.MarshalHandler;
import org.mk300.marshal.minimum.handler.ObjectHandler;
import org.mk300.marshal.minimum.registry.HandlerRegistry;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
public final class OOutputStream extends DataOutputStream {
	
	private static final ObjectHandler undefinedPojoClassHandler = new ObjectHandler();

	private final BAOutputStream underlayBAOut;

	/**
	 * 循環参照があった場合に、無限ループに陥るのを防止する為のカウンタの上限
	 */
	private final static int maxNestLimit = 100;

	/**
	 * 循環参照があった場合に、無限ループに陥るのを防止する為のカウンタ
	 */
	private int nestCount = 0;

	
	
	public OOutputStream(OutputStream out) {
		super(out);
		if (out instanceof BAOutputStream) {
			underlayBAOut = (BAOutputStream) out;
		} else {
			underlayBAOut = null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final void writeObject(Object o) throws IOException {

		if (o == null) {
			writeShort(HandlerRegistry.ID_NULL);
			return;
		}

		if( nestCount++ > maxNestLimit ) {
			// o は無限ループを持っているのでtoString()は危険
			throw new InfiniteLoopException("無限ループです。 data=" + o.getClass().getSimpleName());
		}
		
		
		Class<?> oClazz = o.getClass();
		
		MarshalHandler m;
		
		if( oClazz.isEnum()) {
			
			short id = HandlerRegistry.getClassId(oClazz);
			
			if(id == HandlerRegistry.ID_UNDEFINED_ENUM) {				
				writeShort(HandlerRegistry.ID_UNDEFINED_ENUM);	
				writeString(oClazz.getName());
			} else {
				writeShort(id);
			}
			
			m = HandlerRegistry.getMarshallHandler(HandlerRegistry.ID_ENUM);
//			m.writeObject(this, o);
			
		} else {
			short id = HandlerRegistry.getClassId(oClazz);
		
			if(id == HandlerRegistry.ID_UNDEFINED_POJO) {				
				writeShort(HandlerRegistry.ID_UNDEFINED_POJO);	
				writeString(oClazz.getName());
				m = undefinedPojoClassHandler;
			} else {
				writeShort(id);
				m = HandlerRegistry.getMarshallHandler(id);
			}
			
//			m.writeObject(this, o);
		}
		nestCount--;
	}
	
	public final boolean isUnderlayBAOut() {
		return underlayBAOut != null;
	}
	
	public final int skipIntSize() throws IOException {
		int count = underlayBAOut.size();
		writeInt(Integer.MAX_VALUE);
		return count;
	}
	
	public final void writeIntDirect(int intValue, int index) {
		byte[] buf = underlayBAOut.getBuf();
		buf[index] =   (byte) ((intValue >>> 24) & 0xFF);
		buf[index+1] = (byte) ((intValue >>> 16) & 0xFF);
		buf[index+2] = (byte) ((intValue >>>  8) & 0xFF);
		buf[index+3] = (byte) ((intValue >>>  0) & 0xFF);
	}
	
	public final void write(int b) throws IOException {
		out.write(b);
		incCount(1);
	}

	public final void write(byte b[], int off, int len) throws IOException {
		out.write(b, off, len);
		incCount(len);
	}

	private final void incCount(int value) {
		int temp = written + value;
		if (temp < 0) {
			temp = Integer.MAX_VALUE;
		}
		written = temp;
	}

	@SuppressWarnings("deprecation")
	public void writeString(String str) throws IOException {
		if( str == null) {
			writeByte(0); // null
			return;
		}
		
		int len = str.length();
		
		if(len == 0) {
			writeByte(1); // empty string
			return;
		}
		
		boolean ascii = false;
		if(len < 64) {
			ascii = true;
			for (int i = 0; i < len; i++) {
				if (str.charAt(i) > 127) {
					ascii = false;
					break;
				}
			}
		}
		
		if (ascii) {
			writeByte(2); // small ascii
			writeByte(len);
			if(underlayBAOut != null) {
				underlayBAOut.ensureCapacity2(len);
				int offset = underlayBAOut.size();
				byte[] dest = underlayBAOut.getBuf();
				str.getBytes(0, len, dest, offset);
				incCount(len);
				underlayBAOut.incCount(len);
			} else {
				byte[] asciiArray = new byte[len];
				str.getBytes(0, len, asciiArray, 0);
				write(asciiArray);
			}
			return;
		}
		
		// DataOutputのwriteUTFはUTF-8エンコード後のバイトサイズが65535以下でなければいけない
		//  UTF-8は、1-3バイトの可変なので、全て3バイトと想定して閾値を20000文字とする。
		//  なお、s.length()の戻り値は、サローゲートペアの場合、１文字でも2を返却するので
		//  サローゲートペア文字でも大丈夫
		if(len < 20000) {
			writeByte(3);  // middle string
			writeUTF(str);
		} else {
			byte[] strBytes = str.getBytes("UTF-8");
			writeByte(4); // big string
			writeInt(strBytes.length);
			write(strBytes);
		}
	}
}
