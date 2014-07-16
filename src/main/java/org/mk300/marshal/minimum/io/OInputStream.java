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


import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.mk300.marshal.minimum.MarshalHandler;
import org.mk300.marshal.minimum.registry.HandlerRegistry;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
public final class OInputStream extends DataInputStream {

	private final BAInputStream underlayBAIn;
	
	public OInputStream(InputStream in) {
		super(in);
		if (in instanceof BAInputStream) {
			underlayBAIn = (BAInputStream) in;
		} else {
			underlayBAIn = null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final Object readObject() throws IOException {

		short id = readShort();

		if (id == HandlerRegistry.ID_NULL) {
			return null;
		}

		MarshalHandler m = HandlerRegistry.getMarshallHandler(id);
		Class<?> c = HandlerRegistry.getObjClass(id);

		// Enum is handled by special handler.
		if(c.isEnum()) {
			m = HandlerRegistry.getMarshallHandler(HandlerRegistry.ID_ENUM);
		}
		
		Object obj = m.readObject(this, c);
		return obj;

	}

	public final boolean isUnderlayBAIn() {
		return underlayBAIn != null;
	}

	public final BAInputStream getUnderlayBAIn() {
		return underlayBAIn;
	}
	

	
	
	public String readString() throws IOException {
		String s = null;
		
		byte marker = readByte();
		if(marker == 1) {
			s = readUTF();
		} else if(marker == 2){
			int size = readInt();
			byte[] strBytes = new byte[size];
			readFully(strBytes);
			s = new String(strBytes, "UTF-8");
		}
		
		return s;
	}
}
