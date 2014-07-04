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
import org.mk300.marshal.minimum.io.NaturalNumberIoHelper;
import org.mk300.marshal.minimum.io.OInputStream;
import org.mk300.marshal.minimum.io.OOutputStream;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
public class StringArrayHandler implements MarshalHandler<String[]> {

	@Override
	public void writeObject(OOutputStream out, String[] array) throws IOException {
		NaturalNumberIoHelper.writeNaturalNumber(out, array.length);
		for(int i=0; i< array.length; i++ ) {
			if(array[i] == null) {
				out.writeByte(0);
			} else if(array[i].length() < 20000) {
				out.writeByte(1);
				out.writeUTF(array[i]);
			} else {
				byte[] strBytes = array[i].getBytes("UTF-8");
				out.writeByte(2);
				out.writeInt(strBytes.length);
				out.write(strBytes);
			}
		}
	}

	@Override
	public String[] readObject(OInputStream in, Class<String[]> clazz) throws IOException {
		int size = NaturalNumberIoHelper.readNaturalNumber(in);
		
		String[] array = new String[size];
		for(int i=0; i<size ; i++) {
			byte marker = in.readByte();
			if(marker == 1) {
				array[i] = in.readUTF();
			} else if(marker == 2){
				int len = in.readInt();
				byte[] strBytes = new byte[len];
				in.readFully(strBytes);
				array[i] = new String(strBytes, "UTF-8");
			}
		}			
		return array;
	}
}
