package org.mk300.marshal.minimum.test;

import java.util.concurrent.ConcurrentSkipListMap;

import org.junit.Test;
import org.mk300.marshal.minimum.test.pojo.ComparableObject;
import org.mk300.marshal.minimum.test.pojo.ComparatorForSampleData;
import org.mk300.marshal.minimum.test.pojo.SampleData;

public class ConcurrentSkipListMapTest {
	
	@Test
	public void testNonComaparator() throws Exception {
		ConcurrentSkipListMap<ComparableObject, SampleData> map = new ConcurrentSkipListMap<ComparableObject, SampleData>();
		
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
		ConcurrentSkipListMap<SampleData, SampleData> map = new ConcurrentSkipListMap<SampleData, SampleData>(new ComparatorForSampleData());
		for (int i=0; i< 5; i++) {
			SampleData k = new SampleData();
			k.setP1("" + (100 - i));
			SampleData v = new SampleData();
			map.put(k, v);
		}
		
		TestUtil.testAndPrintHexAndCheck(map);
	}
}
