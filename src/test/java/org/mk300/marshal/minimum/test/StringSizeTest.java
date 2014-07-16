package org.mk300.marshal.minimum.test;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.mk300.marshal.minimum.MinimumMarshaller;
import org.mk300.marshal.minimum.io.OInputStream;
import org.mk300.marshal.minimum.io.OOutputStream;


public class StringSizeTest {

	@Test
	public void sizeAsciiTest() throws Exception {

		int[] sizes = {1, 10 , 20 , 30, 40, 250, 1024, 5000, 5000000};
		
		for(int size : sizes) {
			
			
			String str = RandomStringUtils.randomAlphanumeric(size);

			byte[] bytes = MinimumMarshaller.marshal(str);
			
			System.out.println(str.length() + " binary size is " + bytes.length);

			Object o = MinimumMarshaller.unmarshal(bytes);						
			
			// 正確に復元されていることの検証
			if( ! o.equals(str) ) {
				throw new RuntimeException("オブジェクトが異なります。" + str );
			}
			
			// おまけ 普通のByteArray*Streamも使えるか？
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				OOutputStream oos = new OOutputStream(baos);
				
				oos.writeObject(str);
				
				byte[] bytes2 = baos.toByteArray();
				
				ByteArrayInputStream bais = new ByteArrayInputStream(bytes2);
				OInputStream ois = new OInputStream(bais);
				
				Object o2 = ois.readObject();
				
				// 正確に復元されていることの検証
				if( ! o2.equals(str) ) {
					throw new RuntimeException("（普通のStreamの場合）オブジェクトが異なります" + str );
				}
				
			} finally {
			}
		}

	}
	

	@Test
	public void sizeUtf8Test() throws Exception {

		int[] sizes = {1, 10 , 20 , 30, 40, 250, 1024, 5000, 5000000};
		
		for(int size : sizes) {
			
			
			String str = "あ" + RandomStringUtils.randomAlphanumeric(size);

			byte[] bytes = MinimumMarshaller.marshal(str);
			
			System.out.println(str.length() + " binary size is " + bytes.length);

			Object o = MinimumMarshaller.unmarshal(bytes);						
			
			// 正確に復元されていることの検証
			if( ! o.equals(str) ) {
				throw new RuntimeException("オブジェクトが異なります。" + str );
			}
			
			// おまけ 普通のByteArray*Streamも使えるか？
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				OOutputStream oos = new OOutputStream(baos);
				
				oos.writeObject(str);
				
				byte[] bytes2 = baos.toByteArray();
				
				ByteArrayInputStream bais = new ByteArrayInputStream(bytes2);
				OInputStream ois = new OInputStream(bais);
				
				Object o2 = ois.readObject();
				
				// 正確に復元されていることの検証
				if( ! o2.equals(str) ) {
					throw new RuntimeException("（普通のStreamの場合）オブジェクトが異なります" + str );
				}
				
			} finally {
			}
		}

	}
}
