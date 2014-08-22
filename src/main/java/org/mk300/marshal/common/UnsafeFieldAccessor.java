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

import sun.misc.Unsafe;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
@SuppressWarnings({"restriction", "rawtypes"})
public final class UnsafeFieldAccessor {
	private static Unsafe unsafe;
	static {
		try {
			Field field = Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			unsafe = (Unsafe) field.get(null);
		} catch (Exception e) {
			throw new Error("NG unsafe", e);
		}
	}
	
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
		offset = unsafe.objectFieldOffset(f);
		type = f.getType();
	}
	
	public final Class getType() {
		return type;
	}
	
	public final Object get(Object obj) {
		return unsafe.getObject(obj, offset);
	}

	public final boolean getBoolean(Object obj) {
		return unsafe.getBoolean(obj, offset);
	}

	public final byte getByte(Object obj) {
		return unsafe.getByte(obj, offset);
	}

	public final char getChar(Object obj) {
		return unsafe.getChar(obj, offset);
	}

	public final short getShort(Object obj) {
		return unsafe.getShort(obj, offset);
	}

	public final int getInt(Object obj) {
		return unsafe.getInt(obj, offset);
	}

	public final long getLong(Object obj) {
		return unsafe.getLong(obj, offset);
	}

	public final float getFloat(Object obj) {
		return unsafe.getFloat(obj, offset);
	}

	public final double getDouble(Object obj) {
		return unsafe.getDouble(obj, offset);
	}

	public final void set(Object obj, Object value) {
		unsafe.putObject(obj, offset, value);
	}

	public final void setBoolean(Object obj, boolean z) {
		unsafe.putBoolean(obj, offset, z);
	}

	public final void setByte(Object obj, byte b) {
		unsafe.putByte(obj, offset, b);
	}

	public final void setChar(Object obj, char c) {
		unsafe.putChar(obj, offset, c);
	}

	public final void setShort(Object obj, short s) {
		unsafe.putShort(obj, offset, s);
	}

	public final void setInt(Object obj, int i) {
		unsafe.putInt(obj, offset, i);
	}

	public final void setLong(Object obj, long l) {
		unsafe.putLong(obj, offset, l);
	}

	public final void setFloat(Object obj, float f) {
		unsafe.putFloat(obj, offset, f);
	}
	public final void setDouble(Object obj, double d) {
		unsafe.putDouble(obj, offset, d);
	}

	@Override
	public String toString() {
		return f.toString();
	}
	
}
