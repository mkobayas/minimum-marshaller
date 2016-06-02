package org.mk300.marshal.minimum.test;


import java.util.LinkedHashMap;

import org.junit.Test;


public class LinkedHashMapTest {



	@Test
	public void emptyLinkedHashMapTest() throws Exception {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(16, 0.7f);

		TestUtil.testAndPrintHexAndCheck(map);
	}

	@Test
	public void linkedHashMapReverseTest() throws Exception {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(16, 0.7f);
		
		for (int i=0; i< 5; i++) {
			map.put("" + i, "" + i);
		}
		
		TestUtil.testAndPrintHexAndCheck(map);
	}
	

	@Test
	public void emptyLinkedHashMapReverseTest() throws Exception {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(16, 0.7f, true);

		TestUtil.testAndPrintHexAndCheck(map);
	}

	@Test
	public void linkedHashMapTest() throws Exception {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(16, 0.7f, true);
		
		for (int i=0; i< 5; i++) {
			map.put("" + i, "" + i);
		}

		for (int i=4; i>= 0; i--) {
			map.get("" + i);
		}
		
		TestUtil.testAndPrintHexAndCheck(map);
	}

}
