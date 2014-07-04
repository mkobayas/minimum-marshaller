package org.mk300.marshal.minimum.test;

import java.util.TreeSet;

import org.junit.Test;
import org.mk300.marshal.minimum.MinimumMarshaller;
import org.mk300.marshal.minimum.test.pojo.ComparableObject;
import org.mk300.marshal.minimum.test.pojo.ComparatorForSampleData;
import org.mk300.marshal.minimum.test.pojo.SampleData;

@SuppressWarnings("unchecked")
public class TreeSetTest {
	
	@Test
	public void testNonComaparator() throws Exception {
		TreeSet<ComparableObject> treeSet = new TreeSet<ComparableObject>();
		
		for (int i=0; i< 5; i++) {
			ComparableObject o = new ComparableObject();
			o.setOrder(100 - i);
			treeSet.add(o);
		}
		
		TestUtil.testAndPrintHexAndCheck(treeSet);
		
		byte[] bytes = MinimumMarshaller.marshal(treeSet);

		TreeSet<ComparableObject> o = (TreeSet<ComparableObject>)MinimumMarshaller.unmarshal(bytes);
		
		for(ComparableObject original : treeSet) {
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
		TreeSet<SampleData> treeSet = new TreeSet<SampleData>(new ComparatorForSampleData());
		
		for (int i=0; i< 5; i++) {
			SampleData o = new SampleData();
			o.setP1("" + (100 - i));
			treeSet.add(o);
		}
		
		TestUtil.testAndPrintHexAndCheck(treeSet);
		
		byte[] bytes = MinimumMarshaller.marshal(treeSet);

		TreeSet<SampleData> o = (TreeSet<SampleData>)MinimumMarshaller.unmarshal(bytes);
		
		for(SampleData original : treeSet) {
			System.out.print(original.getP1() + ", ");
		}
		System.out.println();
		
		for(SampleData target : o) {
			System.out.print(target.getP1() + ", ");
		}
		System.out.println();
	}
}
