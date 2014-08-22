package org.mk300.marshal.minimum.test;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.PriorityQueue;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.mk300.marshal.minimum.MinimumMarshaller;


public class StringSizeTest {

	@Test
	public void sizeAsciiTest() throws Exception {

		int[] sizes = {1, 10 , 20 , 30, 40, 250, 1024, 5000, 5000000};
		
		for(int size : sizes) {
			
			
			String str = RandomStringUtils.randomAlphanumeric(size);

			long start = System.nanoTime();
			byte[] bytes = MinimumMarshaller.marshal(str);
			long end = System.nanoTime();
			
			System.out.print(str.length() + " binary size is " + bytes.length);

			Object o = MinimumMarshaller.unmarshal(bytes);
			
			System.out.println(", time(nano)=" + (end -start));					
			
			// 正確に復元されていることの検証
			if( ! o.equals(str) ) {
				System.out.println("original = " + str);
				System.out.println("ummarsha = " + o);
				throw new RuntimeException("オブジェクトが異なります。" + str );
			}
			
			// おまけ 普通のByteArray*Streamも使えるか？
//			try {
//				ByteArrayOutputStream baos = new ByteArrayOutputStream();
//				OOutputStream oos = new OOutputStream(baos);
//				
//				oos.writeObject(str);
//				
//				byte[] bytes2 = baos.toByteArray();
//				
//				ByteArrayInputStream bais = new ByteArrayInputStream(bytes2);
//				OInputStream ois = new OInputStream(bais);
//				
//				Object o2 = ois.readObject();
//				
//				// 正確に復元されていることの検証
//				if( ! o2.equals(str) ) {
//					throw new RuntimeException("（普通のStreamの場合）オブジェクトが異なります" + str );
//				}
//				
//			} finally {
//			}
		}

	}
	

	@Test
	public void sizeUtf8Test() throws Exception {

		int[] sizes = {1, 10 , 20 , 30, 40, 250, 1024, 5000, 5000000};
		
		for(int size : sizes) {
			
			
			String str = "あ" + RandomStringUtils.randomAlphanumeric(size);

			long start = System.nanoTime();
			byte[] bytes = MinimumMarshaller.marshal(str);
			long end = System.nanoTime();
			
			System.out.print(str.length() + " binary size is " + bytes.length);

			Object o = MinimumMarshaller.unmarshal(bytes);
			
			System.out.println(", time(nano)=" + (end -start));
			
			// 正確に復元されていることの検証
			if( ! o.equals(str) ) {
				throw new RuntimeException("オブジェクトが異なります。" + str );
			}
			
			// おまけ 普通のByteArray*Streamも使えるか？
			try {

				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				MinimumMarshaller.marshal(str, baos);
				
				byte[] bytes2 = baos.toByteArray();
				
				ByteArrayInputStream bais = new ByteArrayInputStream(bytes2);
				
				Object o2 = MinimumMarshaller.unmarshal(bais);
				
				// 正確に復元されていることの検証
				if( ! o2.equals(str) ) {
					throw new RuntimeException("（普通のStreamの場合）オブジェクトが異なります" + str );
				}
				
			} finally {
			}
		}

	}
}
