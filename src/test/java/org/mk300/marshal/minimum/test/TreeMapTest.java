package org.mk300.marshal.minimum.test;

import java.util.TreeMap;

import org.junit.Test;
import org.mk300.marshal.minimum.test.pojo.ComparableObject;
import org.mk300.marshal.minimum.test.pojo.ComparatorForSampleData;
import org.mk300.marshal.minimum.test.pojo.SampleData;

public class TreeMapTest {
	
	@Test
	public void testNonComaparator() throws Exception {
		TreeMap<ComparableObject, SampleData> map = new TreeMap<ComparableObject, SampleData>();
		
		for (int i=0; i< 5; i++) {
			ComparableObject k = new ComparableObject();
			k.setOrder(100 - i);
			
			SampleData v = new SampleData();
			map.put(k, v);
		}
		
		TestUtil.testAndPrintHexAndCheck(map);
	}
	
	@Test
	public void testComaparator() throws Exception {
		TreeMap<SampleData, SampleData> map = new TreeMap<SampleData, SampleData>(new ComparatorForSampleData());
		for (int i=0; i< 5; i++) {
			SampleData k = new SampleData();
			k.setP1("" + (100 - i));
			SampleData v = new SampleData();
			map.put(k, v);
		}
		
		TestUtil.testAndPrintHexAndCheck(map);
	}
}
