package org.mk300.marshal.minimum.test;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.concurrent.SynchronousQueue;

import org.apache.commons.io.HexDump;
import org.junit.Test;
import org.mk300.marshal.minimum.MinimumMarshaller;

@SuppressWarnings({"rawtypes"})
public class SynchronousQueueTest {
	@Test
	public void testSynchronousQueueFair() throws Exception {
		SynchronousQueue data = new SynchronousQueue(true);
		testAndPrintHexAndCheck(data);
	}
	@Test
	public void testSynchronousQueueUnFair() throws Exception {
		SynchronousQueue data = new SynchronousQueue(false);
		testAndPrintHexAndCheck(data);
	}

	// SynchronousQueue は equalsメソッドを実装していない・・・
	private void testAndPrintHexAndCheck(SynchronousQueue target) throws Exception{
		try {

			byte[] bytes = MinimumMarshaller.marshal(target);
			
			System.out.println(target.getClass().getSimpleName() + " binary size is " + bytes.length);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			HexDump.dump(bytes, 0, os, 0);
			System.out.println(os.toString());
			System.out.println("");
			
			SynchronousQueue o = (SynchronousQueue)MinimumMarshaller.unmarshal(bytes);
			
			// 正確に復元されていることの検証
			Field f = SynchronousQueue.class.getDeclaredField("transferer");
			f.setAccessible(true);
			
			if( ! f.get(target).getClass().equals(f.get(o).getClass())) {
				throw new RuntimeException("オブジェクトが異なります。target=" + target + ", desr=" + o);
			}
			
		} finally {
		}
		
		// おまけ 普通のByteArray*Streamも使えるか？
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
//			SynchronousQueue o = (SynchronousQueue)ois.readObject();
//			
//			// 正確に復元されていることの検証
//			Field f = SynchronousQueue.class.getDeclaredField("transferer");
//			f.setAccessible(true);
//			
//			if( ! f.get(target).getClass().equals(f.get(o).getClass())) {
//				throw new RuntimeException("オブジェクトが異なります。target=" + target + ", desr=" + o);
//			}
//			
//		} finally {
//		}
	}
}
