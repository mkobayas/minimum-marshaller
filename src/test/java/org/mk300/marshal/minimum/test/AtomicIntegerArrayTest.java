package org.mk300.marshal.minimum.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.atomic.AtomicIntegerArray;

import org.apache.commons.io.HexDump;
import org.junit.Test;
import org.mk300.marshal.minimum.MinimumMarshaller;
import org.mk300.marshal.minimum.io.OInputStream;
import org.mk300.marshal.minimum.io.OOutputStream;

public class AtomicIntegerArrayTest {

	@Test
	public void testAtomicIntegerArray() throws Exception {
		AtomicIntegerArray data = new AtomicIntegerArray(10);

		data.set(0, 0);
		data.set(3, 3);
		data.set(4, 4);
		data.set(5, 5);
		data.set(6, 6);
		data.set(7, 7);
		
		testAndPrintHexAndCheck(data);
	}

	// AtomicIntegerArray は equalsメソッドを実装していない・・・
	private void testAndPrintHexAndCheck(AtomicIntegerArray target) throws Exception{
		try {

			byte[] bytes = MinimumMarshaller.marshal(target);
			
			System.out.println(target.getClass().getSimpleName() + " binary size is " + bytes.length);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			HexDump.dump(bytes, 0, os, 0);
			System.out.println(os.toString());
			System.out.println("");
			
			AtomicIntegerArray o = (AtomicIntegerArray)MinimumMarshaller.unmarshal(bytes);
			
			// 正確に復元されていることの検証
			if( o.length() != target.length()) {
				throw new RuntimeException("オブジェクトが異なります。target=" + target + ", desr=" + o);
			}
			for(int i=0; i<target.length() ; i++) {
				if( o.get(i) != target.get(i)) {
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
			
			AtomicIntegerArray o = (AtomicIntegerArray)ois.readObject();
			
			// 正確に復元されていることの検証
			if( o.length() != target.length()) {
				throw new RuntimeException("オブジェクトが異なります。target=" + target + ", desr=" + o);
			}
			for(int i=0; i<target.length() ; i++) {
				if( o.get(i) != target.get(i)) {
					throw new RuntimeException("オブジェクトが異なります。target=" + target + ", desr=" + o);
					
				}
			}
			
		} finally {
		}
	}
}
