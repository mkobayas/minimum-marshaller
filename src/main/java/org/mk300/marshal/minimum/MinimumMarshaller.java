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

package org.mk300.marshal.minimum;


import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.mk300.marshal.minimum.io.OInput;
import org.mk300.marshal.minimum.io.OInputImpl;
import org.mk300.marshal.minimum.io.OOutput;
import org.mk300.marshal.minimum.io.OOutputImpl;


/**
 *  
 * @author mkobayas@redhat.com
 *
 */
public class MinimumMarshaller {
	
	public static byte[] marshal(Object obj, int estimatedSize) throws IOException {
		OOutput oos = new OOutputImpl(estimatedSize);
		oos.writeObject(obj);
		return oos.toBytes();
	}

	public static byte[] marshal(Object obj) throws IOException {
		return marshal(obj, 32);
	}

	public static Object unmarshal(byte[] buf) throws IOException {
		OInput ois = new OInputImpl(buf);
		return ois.readObject();
	}
	
	
	public static int marshal(Object obj, int estimatedSize, OutputStream out) throws IOException {
		byte[] bin  = marshal(obj, estimatedSize);
		int len = bin.length;
        out.write((len >>> 24) & 0xFF);
        out.write((len >>> 16) & 0xFF);
        out.write((len >>>  8) & 0xFF);
        out.write((len >>>  0) & 0xFF);
		out.write(bin);
		out.flush();
		return len;
	}

	public static int marshal(Object obj, OutputStream out) throws IOException {
		byte[] bin  = marshal(obj, 32);
		int len = bin.length;
        out.write((len >>> 24) & 0xFF);
        out.write((len >>> 16) & 0xFF);
        out.write((len >>>  8) & 0xFF);
        out.write((len >>>  0) & 0xFF);
		out.write(bin);
		out.flush();
		return bin.length;
	}
	
	public static Object unmarshal(InputStream in) throws IOException {
        int ch1 = in.read();
        int ch2 = in.read();
        int ch3 = in.read();
        int ch4 = in.read();
        if ((ch1 | ch2 | ch3 | ch4) < 0)
            throw new EOFException();
        int len = ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0));
        byte[] buf = new byte[len];
        int n = 0;
        while (n < len) {
            int count = in.read(buf, 0 + n, len - n);
            if (count < 0)
                throw new EOFException();
            n += count;
        }
		OInput oi = new OInputImpl(buf);
		return oi.readObject();
	}
}
