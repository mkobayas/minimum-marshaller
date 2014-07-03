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

	public final void writeObject(Object o) throws IOException {

		if (o == null) {
			writeShort(HandlerRegistry.ID_NULL);
			return;
		}

		Class<?> oClazz = o.getClass();
		short id = HandlerRegistry.getClassId(oClazz);
		MarshalHandler m = HandlerRegistry.getMarshallHandler(id);

		writeShort(id);

		m.writeObject(this, o);

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

}
