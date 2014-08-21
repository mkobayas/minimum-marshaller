package org.mk300.marshal.common;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

@SuppressWarnings({"restriction"})
public class TheUnsafe {
	public final static Unsafe unsafe;
	public final static long byteArrayBaseOffset;
	
	static {
		try {
			Field field = Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			unsafe = (Unsafe) field.get(null);
			
			byteArrayBaseOffset = unsafe.arrayBaseOffset(byte[].class);
			
		} catch (Exception e) {
			throw new Error("NG unsafe", e);
		}
	}
	
}
