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

import org.mk300.marshal.minimum.MarshalHandler;
import org.mk300.marshal.minimum.registry.HandlerRegistry;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
public final class OOutputStream extends DataOutputStream {

	private final BAOutputStream underlayBAOut;
	
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

		Class<?> oClazz = o.getClass();
		
		if( oClazz.isEnum()) {
			short id = HandlerRegistry.getClassId(oClazz);
			writeShort(id);

			MarshalHandler m = HandlerRegistry.getMarshallHandler(HandlerRegistry.ID_ENUM);
			m.writeObject(this, o);
			
		} else {
			short id = HandlerRegistry.getClassId(oClazz);
			MarshalHandler m = HandlerRegistry.getMarshallHandler(id);
			writeShort(id);
			m.writeObject(this, o);
		}

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

	public void writeString(String str) throws IOException {
		if( str == null) {
			writeByte(0);
			return;
		}
		
		// DataOutputのwriteUTFはUTF-8エンコード後のバイトサイズが65535以下でなければいけない
		//  UTF-8は、1-3バイトの可変なので、全て3バイトと想定して閾値を20000文字とする。
		//  なお、s.length()の戻り値は、サローゲートペアの場合、１文字でも2を返却するので
		//  サローゲートペア文字でも大丈夫
		if(str.length() < 20000) {
			writeByte(1);
			writeUTF(str);
		} else {
			byte[] strBytes = str.getBytes("UTF-8");
			writeByte(2);
			writeInt(strBytes.length);
			write(strBytes);
		}
	}
}
