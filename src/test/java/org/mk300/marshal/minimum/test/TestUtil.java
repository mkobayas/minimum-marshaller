package org.mk300.marshal.minimum.test;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;

import org.apache.commons.io.HexDump;
import org.mk300.marshal.minimum.MinimumMarshaller;

public class TestUtil {

	public static void testAndPrintHexAndCheck(Object target) throws Exception{
		try {

			byte[] bytes = MinimumMarshaller.marshal(target);
			
			System.out.println(target.getClass().getSimpleName() + " binary size is " + bytes.length);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			HexDump.dump(bytes, 0, os, 0);
			System.out.println(os.toString());
			System.out.println("");
			
			Object o = MinimumMarshaller.unmarshal(bytes);
			
			// 正確に復元されていることの検証
			if( ! deepEquals(target, o) ) {
				throw new RuntimeException("オブジェクトが異なります。target=" + target + ", desr=" + o);
			}
			
		} finally {
		}
		
		// おまけ 普通のByteArray*Streamも使えるか？
		try {

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			MinimumMarshaller.marshal(target, baos);
			
			byte[] bytes = baos.toByteArray();
			
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			
			Object o = MinimumMarshaller.unmarshal(bais);
			
			// 正確に復元されていることの検証
			if( ! deepEquals(target, o) ) {
				throw new RuntimeException("オブジェクトが異なります。target=" + target + ", desr=" + o);
			}
			
		} finally {
		}
	}
	

    public static <T> T testAndPrintHex(T target) throws Exception{
        try {

            byte[] bytes = MinimumMarshaller.marshal(target);
            
            System.out.println(target.getClass().getSimpleName() + " binary size is " + bytes.length);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            HexDump.dump(bytes, 0, os, 0);
            System.out.println(os.toString());
            System.out.println("");
            
            return (T) MinimumMarshaller.unmarshal(bytes);
                        
        } finally {
        }
        
    }
    
	/**
	 * 多次元配列ケースを考慮したチェック。<br>
	 * POJOが多次元配列をフィールドとしてもつ場合は、POJOのequalsメソッドでこのメソッドを利用する事。
	 * 
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static boolean deepEquals(Object o1, Object o2) {
				
		if(o1 == null && o2 == null) {
			return true;
		}
		
		if(o1 == null || o2 == null) {
			return false;
		}
		
		if( !(o1.getClass().isArray() && o2.getClass().isArray()) ) {
			return o1.equals(o2);
		}

		int size1 = Array.getLength(o1);
		int size2 = Array.getLength(o2);
		
		if(size1 != size2) {
			return false;
		}
		
		for(int i=0; i<size1 ; i++) {
			Object e1 = Array.get(o1, i);
			Object e2 = Array.get(o2, i);
			
			if(e1 == null && e2 == null) {
				continue;
			}
			
			if(e1 == null || e2 == null) {
				return false;
			}
			
			boolean tmp = deepEquals(e1, e2);
			if(tmp == false) {
				return false;
			}
		}
		
		return true;
	}
}
