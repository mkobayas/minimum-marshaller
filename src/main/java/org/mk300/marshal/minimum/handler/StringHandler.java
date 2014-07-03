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

import org.mk300.marshal.minimum.MarshalHandler;
import org.mk300.marshal.minimum.io.OInputStream;
import org.mk300.marshal.minimum.io.OOutputStream;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
public class StringHandler implements MarshalHandler {

	@Override
	public void writeObject(OOutputStream out, Object o) throws IOException {
		String s = (String)o;
		
		// DataOutputのwriteUTFはUTF-8エンコード後のバイトサイズが65535以下でなければいけない
		//  UTF-8は、1-3バイトの可変なので、全て3バイトと想定して閾値を20000文字とする。
		//  なお、s.length()の戻り値は、サローゲートペアの場合、１文字でも2を返却するので
		//  サローゲートペア文字でも大丈夫
		if(s.length() < 20000) {
			out.writeByte(1);
			out.writeUTF(s);
		} else {
			byte[] strBytes = s.getBytes("UTF-8");
			out.writeByte(2);
			out.writeInt(strBytes.length);
			out.write(strBytes);
		}
	}

	@Override
	public Object readObject(OInputStream in, Class<?> clazz) throws IOException {
		String s = null;
		
		byte marker = in.readByte();
		if(marker == 1) {
			s = in.readUTF();
		} else if(marker == 2){
			int size = in.readInt();
			byte[] strBytes = new byte[size];
			in.readFully(strBytes);
			s = new String(strBytes, "UTF-8");
		}
		
		return s;
				
	}
}
