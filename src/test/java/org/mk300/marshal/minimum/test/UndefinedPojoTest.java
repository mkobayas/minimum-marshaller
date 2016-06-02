package org.mk300.marshal.minimum.test;


import org.junit.Test;
import org.mk300.marshal.minimum.test.pojo.SampleData;
import org.mk300.marshal.minimum.test.pojo.UndefinedEnum;
import org.mk300.marshal.minimum.test.pojo.UndefinedPojo;


public class UndefinedPojoTest {

	@Test
	public void nestObjectTest() throws Exception {
		UndefinedPojo pojo = new UndefinedPojo();
		
		pojo.setP1(new SampleData());
		pojo.setP2(new SampleData());
		pojo.setEn(UndefinedEnum.E2);
		
		TestUtil.testAndPrintHexAndCheck(pojo);
	}
	

}
