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
import org.mk300.marshal.minimum.handler.ObjectHandler;
import org.mk300.marshal.minimum.registry.HandlerRegistry;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
public final class OInputStream extends DataInputStream {

	private static final ObjectHandler undefinedPojoClassHandler = new ObjectHandler();
	
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

		MarshalHandler m;
		Class<?> c;
				
		short id = readShort();

		if (id == HandlerRegistry.ID_NULL) {
			return null;
		} 
		
		
		if (id == HandlerRegistry.ID_UNDEFINED_POJO || id == HandlerRegistry.ID_UNDEFINED_ENUM) {
			String className = readString();
			try {
				c = classLoad(className);
			} catch (ClassNotFoundException e) {
				throw new IOException(e);
			}
		} else {
			c = HandlerRegistry.getObjClass(id);
		}

		if(c.isEnum()) {
			// Enum is handled by special handler.
			m = HandlerRegistry.getMarshallHandler(HandlerRegistry.ID_ENUM);
		} else if(id == HandlerRegistry.ID_UNDEFINED_POJO) {
			m = undefinedPojoClassHandler;
		} else {
			m = HandlerRegistry.getMarshallHandler(id);
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
	

	
	
	@SuppressWarnings("deprecation")
	public String readString() throws IOException {
		
		switch(readByte()) {
		case 0:
			// null
			return null;
		case 1:
			// empty string
			return "";
		case 2:
			// small ascii
			int size = readByte();
			if(underlayBAIn != null) {
				byte[] src = underlayBAIn.getBuf();
				int offset = underlayBAIn.getPos();
				underlayBAIn.skip(size);
				return new String(src, 0, offset, size);
			} else {
				byte[] strBytes = new byte[size];
				readFully(strBytes);
				return new String(strBytes, 0, 0, size);
			}
		case 3:
			// middle string
			return readUTF();
		case 4:
			// large string
			int size2 = readInt();
			byte[] strBytes2 = new byte[size2];
			readFully(strBytes2);
			return new String(strBytes2, "UTF-8");
		default:
			throw new IOException("Unkwown marker(String).");
		}
	}
	
	
	@SuppressWarnings("rawtypes")
	private static Class classLoad(String className) throws ClassNotFoundException {
		// TODO local caching ?
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			return Class.forName(className, true, Thread.currentThread().getContextClassLoader());
		}
	}
}
