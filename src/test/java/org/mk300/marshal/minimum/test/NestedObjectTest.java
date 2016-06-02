package org.mk300.marshal.minimum.test;


import org.junit.Test;
import org.mk300.marshal.minimum.test.pojo.SampleData;


public class NestedObjectTest {

	@Test
	public void nestObjectTest() throws Exception {
		
		SampleData a1 = new SampleData();
		SampleData a2 = new SampleData();
		SampleData a3 = new SampleData();
		SampleData a4 = new SampleData();

		a1.setP4(a2);
		a2.setP4(a3);
		a3.setP4(a4);
		
		TestUtil.testAndPrintHexAndCheck(a1);
		
		
	}
	

}
