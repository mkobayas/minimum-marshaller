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

import org.mk300.marshal.common.InfiniteLoopException;
import org.mk300.marshal.common.MarshalException;
import org.mk300.marshal.minimum.MarshalHandler;
import org.mk300.marshal.minimum.handler.ObjectHandler;
import org.mk300.marshal.minimum.registry.HandlerRegistry;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
public final class OOutputStream2 {
	
	private final static ObjectHandler undefinedPojoClassHandler = new ObjectHandler();
	
	/**
	 * 循環参照があった場合に、無限ループに陥るのを防止する為のカウンタの上限
	 */
	private final static int maxNestLimit = 100;

	/**
	 * 循環参照があった場合に、無限ループに陥るのを防止する為のカウンタ
	 */
	private int nestCount = 0;
	
	
	private int bufSize;
	
	private byte[] buf;
	private int pos;
	
	public OOutputStream2(int initialBufSize) {
		this.bufSize = initialBufSize;
		buf = new byte[bufSize];
		pos = 0;
	}
	
	public final void write(int b) throws IOException {
		if(pos == bufSize) ensureCapacity(1);
		buf[pos++] = (byte)b;
	}

	public final void write(byte[] b) throws IOException {
		int len = b.length;
		ensureCapacity(len);
		System.arraycopy(b, 0, buf, pos, len);
		pos += len;
	}

	public final void write(byte[] b, int off, int len) throws IOException {
		ensureCapacity(len);
		System.arraycopy(b, off, buf, pos, len);
		pos += len;
	}
    
    public final void writeBoolean(boolean v) throws IOException {
    	if(pos == bufSize) ensureCapacity(1);
		buf[pos++] = (byte)(v ? 1 : 0);
    }

    public final void writeByte(int v) throws IOException {
    	if(pos == bufSize) ensureCapacity(1);
		buf[pos++] = (byte)v;
    }

    public final void writeShort(short v) throws IOException {
    	ensureCapacity(2);
		byte[] buf = this.buf;
		buf[pos++] = (byte)(v >>> 8);
		buf[pos++] = (byte)v;
    }
    
    public final void writeChar(char v) throws IOException {
    	ensureCapacity(2);
		byte[] buf = this.buf;
		buf[pos++] = (byte)(v >>> 8);
		buf[pos++] = (byte)v;
    }

    public final void writeInt(int v) throws IOException {
    	ensureCapacity(4);
		byte[] buf = this.buf;
    	buf[pos++] = (byte)(v >>> 24);
    	buf[pos++] = (byte)(v >>> 16);
    	buf[pos++] = (byte)(v >>> 8);
    	buf[pos++] = (byte)v;
    }

    public final void writeLong(long v) throws IOException {
    	ensureCapacity(8);
		byte[] buf = this.buf;
		buf[pos++] = (byte)(v >>> 56);
		buf[pos++] = (byte)(v >>> 48);
		buf[pos++] = (byte)(v >>> 40);
		buf[pos++] = (byte)(v >>> 32);
		buf[pos++] = (byte)(v >>> 24);
		buf[pos++] = (byte)(v >>> 16);
		buf[pos++] = (byte)(v >>> 8);
		buf[pos++] = (byte)v;
    }

    public final void writeFloat(float f) throws IOException {
    	int v = Float.floatToIntBits(f);
    	ensureCapacity(4);
		byte[] buf = this.buf;
    	buf[pos++] = (byte)(v >>> 24);
    	buf[pos++] = (byte)(v >>> 16);
    	buf[pos++] = (byte)(v >>> 8);
    	buf[pos++] = (byte)v;
    }

    public final void writeDouble(double d) throws IOException {
    	long v = Double.doubleToLongBits(d);
    	ensureCapacity(8);
		byte[] buf = this.buf;
		buf[pos++] = (byte)(v >>> 56);
		buf[pos++] = (byte)(v >>> 48);
		buf[pos++] = (byte)(v >>> 40);
		buf[pos++] = (byte)(v >>> 32);
		buf[pos++] = (byte)(v >>> 24);
		buf[pos++] = (byte)(v >>> 16);
		buf[pos++] = (byte)(v >>> 8);
		buf[pos++] = (byte)v;
    }

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
			m.writeObject(this, o);
			
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
			
			m.writeObject(this, o);
		}
		nestCount--;
    }
	

	public final void writeString(String str) throws IOException {
		if( str == null) {
			writeByte(0); // null
			return;
		}
		
		int len = str.length();
		
		if(len == 0) {
			writeByte(1); // empty string
			return;
		}
		
		boolean ascii = true;
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
			ensureCapacity(pos);
			str.getBytes(0, len, buf, pos);
			pos += len;
			return;
		}
		
		// TODO mid size string
		byte[] strBytes = str.getBytes("UTF-8");
		writeByte(4); // big string
		writeInt(strBytes.length);
		write(strBytes);
	}
	
	
	private final void ensureCapacity(int req) throws MarshalException {
		if (bufSize - pos >= req) return;
		if (pos + req > Integer.MAX_VALUE) {
			throw new MarshalException("Overflow. maxBufSize=" + Integer.MAX_VALUE + ", current=" + pos + ", required: " + req);
		}
		
		bufSize = Math.min(bufSize * 2, Integer.MAX_VALUE);
		byte[] newBuf = new byte[bufSize];
		System.arraycopy(buf, 0, newBuf, 0, pos);
		buf = newBuf;
	}
	
	public final byte[] toBytes () {
		byte[] b = new byte[pos];
		System.arraycopy(buf, 0, b, 0, pos);
		return b;
	}


	public final void flush() throws IOException {
	}

	public final void close() throws IOException {
	}

    public final int size() {
        return pos;
    }
	
	public final int skipIntSize() throws IOException {
		int count = pos;
		pos +=4;
		return count;
	}
	
	public final void writeIntDirect(int intValue, int index) {
		byte[] buf = this.buf; /* avoid getfield opcode */
		buf[index] =   (byte) ((intValue >>> 24) & 0xFF);
		buf[index+1] = (byte) ((intValue >>> 16) & 0xFF);
		buf[index+2] = (byte) ((intValue >>>  8) & 0xFF);
		buf[index+3] = (byte) ((intValue >>>  0) & 0xFF);
	}
	
}
