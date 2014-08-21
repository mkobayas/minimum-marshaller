package org.mk300.marshal.minimum.test;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;

import org.apache.commons.io.HexDump;
import org.mk300.marshal.minimum.MinimumMarshaller;
import org.mk300.marshal.minimum.io.OInputStream;
import org.mk300.marshal.minimum.io.OOutputStream;

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
			if( ! o.equals(target) ) {
				throw new RuntimeException("オブジェクトが異なります。target=" + target + ", desr=" + o);
			}
			
		} finally {
		}
		
//		// おまけ 普通のByteArray*Streamも使えるか？
//		try {
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			OOutputStream oos = new OOutputStream(baos);
//			
//			oos.writeObject(target);
//			
//			byte[] bytes = baos.toByteArray();
//			
//			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
//			OInputStream ois = new OInputStream(bais);
//			
//			Object o = ois.readObject();
//			
//			// 正確に復元されていることの検証
//			if( ! o.equals(target) ) {
//				throw new RuntimeException("オブジェクトが異なります。target=" + target + ", desr=" + o);
//			}
//			
//		} finally {
//		}
	}
	
	public static void testAndPrintHexAndCheckForArray(Object target) throws Exception{
		try {

			byte[] bytes = MinimumMarshaller.marshal(target);
			
			System.out.println(target.getClass().getSimpleName() + " binary size is " + bytes.length);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			HexDump.dump(bytes, 0, os, 0);
			System.out.println(os.toString());
			System.out.println("");
			
			Object o = MinimumMarshaller.unmarshal(bytes);
			
			// 正確に復元されていることの検証
			int size = Array.getLength(target);
			for(int i=0; i< size; i++) {
				Object original = Array.get(target, i);
				Object ret = Array.get(o, i);
				if( ! original.equals(ret) ) {
					throw new RuntimeException("配列オブジェクトが異なります。" + target );
				}
			}
			
		} finally {
		}
		
		

		// おまけ 普通のByteArray*Streamも使えるか？
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			OOutputStream oos = new OOutputStream(baos);
			oos.writeObject(target);
			byte[] bytes = baos.toByteArray();
			
			
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			OInputStream ois = new OInputStream(bais);
			Object o = ois.readObject();
			
			
			// 正確に復元されていることの検証
			int size = Array.getLength(target);
			for(int i=0; i< size; i++) {
				Object original = Array.get(target, i);
				Object ret = Array.get(o, i);
				if( ! original.equals(ret) ) {
					throw new RuntimeException("配列オブジェクトが異なります。" + target );
				}
			}
			
		} finally {
		}
	}
}
