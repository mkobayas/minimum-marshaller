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


import java.io.IOException;
import java.io.UTFDataFormatException;

import org.mk300.marshal.minimum.MarshalHandler;
import org.mk300.marshal.minimum.handler.ObjectHandler;
import org.mk300.marshal.minimum.registry.HandlerRegistry;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
public final class OInput {

	private static final ObjectHandler undefinedPojoClassHandler = new ObjectHandler();
	
	private byte[] buf;
	private int pos;
	
	public OInput(byte[] buf) {
		this.buf = buf;
		pos = 0;
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
	
	
	@SuppressWarnings("deprecation")
	public final String readString() throws IOException {
		byte mark = readByte();
		
		switch(mark) {
		case 0:
			// null
			return null;
		case 1:
			// empty string
			return "";
		case 2:
			// small ascii
			int size = readByte();
			String str = new String(buf, 0, pos, size);
			pos += size;
			return str;
		case 3:
			// large string
			return readUTF();
		default:
			throw new IOException("Unkwown marker(String). mark=" + mark);
		}
	}
	
	
	public final void readFully(byte[] dest) {
		System.arraycopy(buf, pos, dest, 0, dest.length);
		pos += dest.length;
		
	}

	@SuppressWarnings("rawtypes")
	private final static Class classLoad(String className) throws ClassNotFoundException {
		// TODO local caching ?
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			return Class.forName(className, true, Thread.currentThread().getContextClassLoader());
		}
	}

	public final byte readByte() {
		return buf[pos++];
	}

	public final boolean readBoolean() {
		return 1 == (buf[pos++]);
	}

	public final int readInt() {
		byte[] buf = this.buf;
		int pos = this.pos;
		this.pos = pos + 4;
		return (buf[pos] & 0xFF) << 24 //
			| (buf[pos + 1] & 0xFF) << 16 //
			| (buf[pos + 2] & 0xFF) << 8 //
			| buf[pos + 3] & 0xFF;
		
	}

	public final long readLong() {
		byte[] buf = this.buf;
		int pos = this.pos;
		this.pos = pos + 8;
		return (long)buf[pos] << 56 //
			| (long)(buf[pos + 1] & 0xFF) << 48 //
			| (long)(buf[pos + 2] & 0xFF) << 40 //
			| (long)(buf[pos + 3] & 0xFF) << 32 //
			| (long)(buf[pos + 4] & 0xFF) << 24 //
			| (buf[pos + 5] & 0xFF) << 16 //
			| (buf[pos + 6] & 0xFF) << 8 //
			| buf[pos + 7] & 0xFF;
	}

	public final short readShort() {
		byte[] buf = this.buf;
		int pos = this.pos;
		this.pos = pos + 2;
		return (short)( (buf[pos] & 0xFF) << 8 | buf[pos + 1] & 0xFF );
	}

	public final char readChar() {
		byte[] buf = this.buf;
		int pos = this.pos;
		this.pos = pos + 2;
		return (char)( (buf[pos] & 0xFF) << 8 | buf[pos + 1] & 0xFF );
	}

	public final float readFloat() {
		return Float.intBitsToFloat(readInt());
	}

	public final double readDouble() {
		return Double.longBitsToDouble(readLong());
	}

	public final void skip(int n) {
		pos += n;
	}
	
	public final OInput branch() {
		OInput branch = new OInput(buf);
		
		branch.pos = this.pos;
		
		return branch;
	}

    public final String readUTF() throws IOException {
        int utflen = readInt();
        
        byte[] bytearr = buf;
        char[] chararr = new char[utflen];

        utflen = pos + utflen;
        int c, char2, char3;
        int count = pos;
        int chararr_count=0;

        while (count < utflen) {
            c = (int) bytearr[count] & 0xff;
            if (c > 127) break;
            count++;
            chararr[chararr_count++]=(char)c;
        }

        while (count < utflen) {
            c = (int) bytearr[count] & 0xff;
            switch (c >> 4) {
                case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7:
                    /* 0xxxxxxx*/
                    count++;
                    chararr[chararr_count++]=(char)c;
                    break;
                case 12: case 13:
                    /* 110x xxxx   10xx xxxx*/
                    count += 2;
                    if (count > utflen)
                        throw new UTFDataFormatException(
                            "malformed input: partial character at end");
                    char2 = (int) bytearr[count-1];
                    if ((char2 & 0xC0) != 0x80)
                        throw new UTFDataFormatException(
                            "malformed input around byte " + count);
                    chararr[chararr_count++]=(char)(((c & 0x1F) << 6) |
                                                    (char2 & 0x3F));
                    break;
                case 14:
                    /* 1110 xxxx  10xx xxxx  10xx xxxx */
                    count += 3;
                    if (count > utflen)
                        throw new UTFDataFormatException(
                            "malformed input: partial character at end");
                    char2 = (int) bytearr[count-2];
                    char3 = (int) bytearr[count-1];
                    if (((char2 & 0xC0) != 0x80) || ((char3 & 0xC0) != 0x80))
                        throw new UTFDataFormatException(
                            "malformed input around byte " + (count-1));
                    chararr[chararr_count++]=(char)(((c     & 0x0F) << 12) |
                                                    ((char2 & 0x3F) << 6)  |
                                                    ((char3 & 0x3F) << 0));
                    break;
                default:
                    /* 10xx xxxx,  1111 xxxx */
                    throw new UTFDataFormatException(
                        "malformed input around byte " + count);
            }
        }
        
        pos = count;
        
        return new String(chararr, 0, chararr_count);
    }
}
