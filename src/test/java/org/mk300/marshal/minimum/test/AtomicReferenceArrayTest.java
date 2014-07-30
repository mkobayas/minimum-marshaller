package org.mk300.marshal.minimum.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReferenceArray;

import org.apache.commons.io.HexDump;
import org.junit.Test;
import org.mk300.marshal.minimum.MinimumMarshaller;
import org.mk300.marshal.minimum.io.OInputStream;
import org.mk300.marshal.minimum.io.OOutputStream;

@SuppressWarnings({"rawtypes", "unchecked"})
public class AtomicReferenceArrayTest {
	@Test
	public void testAtomicReferenceArray() throws Exception {
		AtomicReferenceArray data = new AtomicReferenceArray(10);

		data.set(0, new Date(0));
		data.set(3, new Date(3));
		data.set(4, new Date(4));
		data.set(5, new Date(5));
		data.set(6, new Date(6));
		data.set(7, new Date(7));
		
		testAndPrintHexAndCheck(data);
	}

	// AtomicIntegerArray は equalsメソッドを実装していない・・・
	private void testAndPrintHexAndCheck(AtomicReferenceArray target) throws Exception{
		try {

			byte[] bytes = MinimumMarshaller.marshal(target);
			
			System.out.println(target.getClass().getSimpleName() + " binary size is " + bytes.length);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			HexDump.dump(bytes, 0, os, 0);
			System.out.println(os.toString());
			System.out.println("");
			
			AtomicReferenceArray o = (AtomicReferenceArray)MinimumMarshaller.unmarshal(bytes);
			
			// 正確に復元されていることの検証
			if( o.length() != target.length()) {
				throw new RuntimeException("オブジェクトが異なります。target=" + target + ", desr=" + o);
			}
			
			
			for(int i=0; i<target.length() ; i++) {
				if(o.get(i) == null && target.get(i) == null) {
					continue;
				}
				
				if(o.get(i) == null || target.get(i) == null) {
					throw new RuntimeException("オブジェクトが異なります。target=" + target + ", desr=" + o);
				}
				
				if( ! o.get(i).equals(target.get(i))) {
					throw new RuntimeException("オブジェクトが異なります。target=" + target + ", desr=" + o);
					
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
			
			AtomicReferenceArray o = (AtomicReferenceArray)ois.readObject();
			
			// 正確に復元されていることの検証
			if( o.length() != target.length()) {
				throw new RuntimeException("オブジェクトが異なります。target=" + target + ", desr=" + o);
			}
			
			
			for(int i=0; i<target.length() ; i++) {
				if(o.get(i) == null && target.get(i) == null) {
					continue;
				}
				
				if(o.get(i) == null || target.get(i) == null) {
					throw new RuntimeException("オブジェクトが異なります。target=" + target + ", desr=" + o);
				}
				
				if( ! o.get(i).equals(target.get(i))) {
					throw new RuntimeException("オブジェクトが異なります。target=" + target + ", desr=" + o);
					
				}
			}
			
		} finally {
		}
	}
}
