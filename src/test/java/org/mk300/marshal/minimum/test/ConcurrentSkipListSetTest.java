package org.mk300.marshal.minimum.test;

import java.util.concurrent.ConcurrentSkipListSet;

import org.junit.Test;
import org.mk300.marshal.minimum.MinimumMarshaller;
import org.mk300.marshal.minimum.test.pojo.ComparableObject;
import org.mk300.marshal.minimum.test.pojo.ComparatorForSampleData;
import org.mk300.marshal.minimum.test.pojo.SampleData;

@SuppressWarnings("unchecked")
public class ConcurrentSkipListSetTest {
	
	@Test
	public void testNonComaparator() throws Exception {
		ConcurrentSkipListSet<ComparableObject> set = new ConcurrentSkipListSet<ComparableObject>();
		
		for (int i=0; i< 5; i++) {
			ComparableObject o = new ComparableObject();
			o.setOrder(100 - i);
			set.add(o);
		}
		
		TestUtil.testAndPrintHexAndCheck(set);
		
		byte[] bytes = MinimumMarshaller.marshal(set);

		ConcurrentSkipListSet<ComparableObject> o = (ConcurrentSkipListSet<ComparableObject>)MinimumMarshaller.unmarshal(bytes);
		
		for(ComparableObject original : set) {
			System.out.print(original.getOrder() + ", ");
		}
		System.out.println();
		
		for(ComparableObject target : o) {
			System.out.print(target.getOrder() + ", ");
		}
		System.out.println();
	}
	
	@Test
	public void testComaparator() throws Exception {
		ConcurrentSkipListSet<SampleData> set = new ConcurrentSkipListSet<SampleData>(new ComparatorForSampleData());
		
		for (int i=0; i< 5; i++) {
			SampleData o = new SampleData();
			o.setP1("" + (100 - i));
			set.add(o);
		}
		
		TestUtil.testAndPrintHexAndCheck(set);
		
		byte[] bytes = MinimumMarshaller.marshal(set);

		ConcurrentSkipListSet<SampleData> o = (ConcurrentSkipListSet<SampleData>)MinimumMarshaller.unmarshal(bytes);
		
		for(SampleData original : set) {
			System.out.print(original.getP1() + ", ");
		}
		System.out.println();
		
		for(SampleData target : o) {
			System.out.print(target.getP1() + ", ");
		}
		System.out.println();
	}
}
