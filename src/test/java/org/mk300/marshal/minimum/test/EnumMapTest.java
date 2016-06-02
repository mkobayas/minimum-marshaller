package org.mk300.marshal.minimum.test;


import java.util.EnumMap;

import org.junit.Test;
import org.mk300.marshal.minimum.test.pojo.MyEnum;



@SuppressWarnings({"unchecked", "rawtypes"})
public class EnumMapTest {

	@Test
	public void emptyEnumMapTest() throws Exception {
		EnumMap<MyEnum, String> map = new EnumMap(MyEnum.class);
		
		TestUtil.testAndPrintHexAndCheck(map);
	}
	

	@Test
	public void mapEnumMap() throws Exception {
		EnumMap<MyEnum, String> map = new EnumMap(MyEnum.class);

		map.put(MyEnum.E1, "hoge1");
		map.put(MyEnum.E3, "hoge3");
		
		TestUtil.testAndPrintHexAndCheck(map);
	}
	

}
