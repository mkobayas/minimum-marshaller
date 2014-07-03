package org.mk300.marshal.minimum.test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.mk300.marshal.minimum.test.pojo.PrimitiveWrapperData;


public class CollectionDataTest {


	@Test
	public void emptyListTest() throws Exception {
		List<PrimitiveWrapperData> list = new ArrayList<PrimitiveWrapperData>();
		
		TestUtil.testAndPrintHexAndCheck(list);
	}
	

	@Test
	public void listTest() throws Exception {
		List<PrimitiveWrapperData> list = new ArrayList<PrimitiveWrapperData>();
		
		for (int i=0; i< 5; i++) {
			PrimitiveWrapperData p = new PrimitiveWrapperData();

			p.setBooleanValue(true);
			p.setByteValue((byte)1);
			p.setCharValue('h');
			p.setDoubleValue(111.22);
			p.setFloatValue(11.333f);
			p.setIntValue(987);
			p.setLongValue(Long.MAX_VALUE);
			p.setShortValue(Short.MAX_VALUE);
			
			list.add(p);
		}
		
		TestUtil.testAndPrintHexAndCheck(list);
	}
	

	@Test
	public void listlistTest() throws Exception {
		List<List<PrimitiveWrapperData>> list = new ArrayList<List<PrimitiveWrapperData>>();
		
		for(int j=0; j<5; j++) {
			List<PrimitiveWrapperData> nest = new ArrayList<PrimitiveWrapperData>();
			for (int i=0; i< 5; i++) {
				PrimitiveWrapperData p = new PrimitiveWrapperData();
	
				p.setBooleanValue(true);
				p.setByteValue((byte)1);
				p.setCharValue('h');
				p.setDoubleValue(111.22);
				p.setFloatValue(11.333f);
				p.setIntValue(987);
				p.setLongValue(Long.MAX_VALUE);
				p.setShortValue(Short.MAX_VALUE);
				
				nest.add(p);
			}
			list.add(nest);
		}
		TestUtil.testAndPrintHexAndCheck(list);
	}



	@Test
	public void emptyMapTest() throws Exception {
		Map<String, PrimitiveWrapperData> map = new HashMap<String, PrimitiveWrapperData>();

		
		TestUtil.testAndPrintHexAndCheck(map);
	}

	@Test
	public void mapTest() throws Exception {
		Map<String, PrimitiveWrapperData> map = new HashMap<String, PrimitiveWrapperData>();
		
		for (int i=0; i< 5; i++) {
			PrimitiveWrapperData p = new PrimitiveWrapperData();

			p.setBooleanValue(true);
			p.setByteValue((byte)1);
			p.setCharValue('h');
			p.setDoubleValue(111.22);
			p.setFloatValue(11.333f);
			p.setIntValue(987);
			p.setLongValue(Long.MAX_VALUE);
			p.setShortValue(Short.MAX_VALUE);
			
			map.put("" + i, p);
		}
		
		TestUtil.testAndPrintHexAndCheck(map);
	}
	


	@Test
	public void emptySetTest() throws Exception {
		Set<PrimitiveWrapperData> set = new HashSet<PrimitiveWrapperData>();

		
		TestUtil.testAndPrintHexAndCheck(set);
	}


	@Test
	public void setTest() throws Exception {
		Set<PrimitiveWrapperData> set = new HashSet<PrimitiveWrapperData>();
		

		for (int i=0; i< 5; i++) {
			PrimitiveWrapperData p = new PrimitiveWrapperData();
	
			p.setBooleanValue(true);
			p.setByteValue((byte)i);
			p.setCharValue('h');
			p.setDoubleValue(111.22);
			p.setFloatValue(11.333f);
			p.setIntValue(987);
			p.setLongValue(Long.MAX_VALUE);
			p.setShortValue(Short.MAX_VALUE);
			
			set.add(p);
		}
		
		TestUtil.testAndPrintHexAndCheck(set);
	}
}
