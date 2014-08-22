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
package org.mk300.marshal.common;

import java.lang.reflect.Field;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
@SuppressWarnings({"restriction", "rawtypes"})
public final class UnsafeFieldAccessor {
	
	public static UnsafeFieldAccessor[] convert(Field[] f) {
		UnsafeFieldAccessor[] ret = new UnsafeFieldAccessor[f.length];
		
		for(int i=0; i<f.length; i++) {
			ret[i] = new UnsafeFieldAccessor(f[i]);
		}
		
		return ret;
	}
	
	private final long offset;
	private final Field f;
	private final Class type;
	
	private UnsafeFieldAccessor(Field f) {
		this.f = f;
		offset = TheUnsafe.unsafe.objectFieldOffset(f);
		type = f.getType();
	}
	
	public final Class getType() {
		return type;
	}
	
	public final Object get(Object obj) {
		return TheUnsafe.unsafe.getObject(obj, offset);
	}

	public final boolean getBoolean(Object obj) {
		return TheUnsafe.unsafe.getBoolean(obj, offset);
	}

	public final byte getByte(Object obj) {
		return TheUnsafe.unsafe.getByte(obj, offset);
	}

	public final char getChar(Object obj) {
		return TheUnsafe.unsafe.getChar(obj, offset);
	}

	public final short getShort(Object obj) {
		return TheUnsafe.unsafe.getShort(obj, offset);
	}

	public final int getInt(Object obj) {
		return TheUnsafe.unsafe.getInt(obj, offset);
	}

	public final long getLong(Object obj) {
		return TheUnsafe.unsafe.getLong(obj, offset);
	}

	public final float getFloat(Object obj) {
		return TheUnsafe.unsafe.getFloat(obj, offset);
	}

	public final double getDouble(Object obj) {
		return TheUnsafe.unsafe.getDouble(obj, offset);
	}

	public final void set(Object obj, Object value) {
		TheUnsafe.unsafe.putObject(obj, offset, value);
	}

	public final void setBoolean(Object obj, boolean z) {
		TheUnsafe.unsafe.putBoolean(obj, offset, z);
	}

	public final void setByte(Object obj, byte b) {
		TheUnsafe.unsafe.putByte(obj, offset, b);
	}

	public final void setChar(Object obj, char c) {
		TheUnsafe.unsafe.putChar(obj, offset, c);
	}

	public final void setShort(Object obj, short s) {
		TheUnsafe.unsafe.putShort(obj, offset, s);
	}

	public final void setInt(Object obj, int i) {
		TheUnsafe.unsafe.putInt(obj, offset, i);
	}

	public final void setLong(Object obj, long l) {
		TheUnsafe.unsafe.putLong(obj, offset, l);
	}

	public final void setFloat(Object obj, float f) {
		TheUnsafe.unsafe.putFloat(obj, offset, f);
	}
	public final void setDouble(Object obj, double d) {
		TheUnsafe.unsafe.putDouble(obj, offset, d);
	}

	@Override
	public String toString() {
		return f.toString();
	}
	
}
