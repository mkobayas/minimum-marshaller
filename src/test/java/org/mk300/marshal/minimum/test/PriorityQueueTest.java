package org.mk300.marshal.minimum.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

import org.apache.commons.io.HexDump;
import org.junit.Test;
import org.mk300.marshal.minimum.MinimumMarshaller;
import org.mk300.marshal.minimum.test.pojo.ComparatorForSampleData;
import org.mk300.marshal.minimum.test.pojo.SampleData;

@SuppressWarnings({"rawtypes", "unchecked"})
public class PriorityQueueTest {
	@Test
	public void testPriorityQueue() throws Exception {
		PriorityQueue data = new PriorityQueue(10);

		data.add(new Date(0));
		data.add(new Date(3));
		data.add(new Date(4));
		data.poll();
		testAndPrintHexAndCheck(data);
	}
	
	@Test
	public void testPriorityQueueWithComparator() throws Exception {
		PriorityQueue data = new PriorityQueue(10, new ComparatorForSampleData());

		{
			SampleData s = new SampleData();
			s.setP1("ccc");
			data.add(s);
		}
		{
			SampleData s = new SampleData();
			s.setP1("ddd");
			data.add(s);
		}
		{
			SampleData s = new SampleData();
			s.setP1("bbb");
			data.add(s);
		}
		{
			SampleData s = new SampleData();
			s.setP1("aaa");
			data.add(s);
		}
		
		data.poll();
		testAndPrintHexAndCheck(data);
	}
	// PriorityQueue は equalsメソッドを実装していない・・・
	private void testAndPrintHexAndCheck(PriorityQueue<Date> target) throws Exception{
		try {

			byte[] bytes = MinimumMarshaller.marshal(target);
			
			System.out.println(target.getClass().getSimpleName() + " binary size is " + bytes.length);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			HexDump.dump(bytes, 0, os, 0);
			System.out.println(os.toString());
			System.out.println("");
			
			PriorityQueue<Object> o = (PriorityQueue<Object>)MinimumMarshaller.unmarshal(bytes);
			
			// 正確に復元されていることの検証
			if( o.size() != target.size()) {
				throw new RuntimeException("オブジェクトが異なります。target=" + target + ", desr=" + o);
			}
	
			Object[] desr = o.toArray(new Object[0]);
			Object[] origin = target.toArray(new Object[0]);
			
			for(int i=0; i<desr.length ; i++) {
				if(desr[i] == null && origin[i] == null) {
					continue;
				}
				
				if(desr[i] == null || origin[i] == null) {
					throw new RuntimeException("オブジェクトが異なります。target=" + target + ", desr=" + o);
				}
				
				if( ! desr[i].equals(origin[i])) {
					throw new RuntimeException("オブジェクトが異なります。target=" + target + ", desr=" + o);
					
				}
			}
		} finally {
		}
		
		// おまけ 普通のByteArray*Streamも使えるか？
		try {

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			MinimumMarshaller.marshal(target, baos);
			
			byte[] bytes = baos.toByteArray();
			
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			
			PriorityQueue<Date> o = (PriorityQueue<Date>)MinimumMarshaller.unmarshal(bais);
			
			// 正確に復元されていることの検証
			if( o.size() != target.size()) {
				throw new RuntimeException("オブジェクトが異なります。target=" + target + ", desr=" + o);
			}
	
			Object[] desr = o.toArray(new Object[0]);
			Object[] origin = target.toArray(new Object[0]);
			
			for(int i=0; i<desr.length ; i++) {
				if(desr[i] == null && origin[i] == null) {
					continue;
				}
				
				if(desr[i] == null || origin[i] == null) {
					throw new RuntimeException("オブジェクトが異なります。target=" + target + ", desr=" + o);
				}
				
				if( ! desr[i].equals(origin[i])) {
					throw new RuntimeException("オブジェクトが異なります。target=" + target + ", desr=" + o);
					
				}
			}
			
		} finally {
		}
	}
}
