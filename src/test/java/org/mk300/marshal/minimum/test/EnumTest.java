package org.mk300.marshal.minimum.test;

import org.junit.Test;
import org.mk300.marshal.minimum.test.pojo.MyEnum;
import org.mk300.marshal.minimum.test.pojo.MyEnumObject;

public class EnumTest {

	@Test
	public void testEnumObject() throws Exception {
		MyEnumObject obj = new MyEnumObject();
		obj.setMyEnum(MyEnum.E2);
		TestUtil.testAndPrintHexAndCheck(obj);
	}
	
	@Test
	public void testEnum() throws Exception {
		MyEnum e = MyEnum.E2;
		TestUtil.testAndPrintHexAndCheck(e);
	}
	
}
