package org.mk300.marshal.minimum.test;

import java.util.EnumSet;

import org.junit.Test;
import org.mk300.marshal.minimum.test.pojo.MyEnum;

@SuppressWarnings("rawtypes")
public class EnumSetTest {

	
	@Test
	public void testEnumSet0() throws Exception {
		
		EnumSet set = EnumSet.noneOf(MyEnum.class);		
		TestUtil.testAndPrintHexAndCheck(set);
	}

	@Test
	public void testEnumSet1() throws Exception {
		
		EnumSet set = EnumSet.of(MyEnum.E2);
		TestUtil.testAndPrintHexAndCheck(set);
	}
	

	@Test
	public void testEnumSet3() throws Exception {
		
		EnumSet set = EnumSet.of(MyEnum.E1, MyEnum.E2, MyEnum.E3);
		TestUtil.testAndPrintHexAndCheck(set);
	}
}
