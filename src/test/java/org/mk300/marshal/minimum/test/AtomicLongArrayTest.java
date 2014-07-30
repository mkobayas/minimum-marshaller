package org.mk300.marshal.minimum.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.atomic.AtomicLongArray;

import org.apache.commons.io.HexDump;
import org.junit.Test;
import org.mk300.marshal.minimum.MinimumMarshaller;
import org.mk300.marshal.minimum.io.OInputStream;
import org.mk300.marshal.minimum.io.OOutputStream;

public class AtomicLongArrayTest {

	@Test
	public void testAtomicLongArrayArray() throws Exception {
		AtomicLongArray data = new AtomicLongArray(10);

		data.set(0, 0l);
		data.set(3, 3l);
		data.set(4, 4l);
		data.set(5, 5l);
		data.set(6, 6l);
		data.set(7, 7l);
		
		testAndPrintHexAndCheck(data);
	}

	// AtomicIntegerArray は equalsメソッドを実装していない・・・
	private void testAndPrintHexAndCheck(AtomicLongArray target) throws Exception{
		try {

			byte[] bytes = MinimumMarshaller.marshal(target);
			
			System.out.println(target.getClass().getSimpleName() + " binary size is " + bytes.length);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			HexDump.dump(bytes, 0, os, 0);
			System.out.println(os.toString());
			System.out.println("");
			
			AtomicLongArray o = (AtomicLongArray)MinimumMarshaller.unmarshal(bytes);
			
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
			
			AtomicLongArray o = (AtomicLongArray)ois.readObject();
			
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
