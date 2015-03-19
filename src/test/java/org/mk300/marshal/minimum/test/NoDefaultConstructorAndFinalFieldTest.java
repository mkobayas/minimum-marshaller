package org.mk300.marshal.minimum.test;


import org.junit.Test;
import org.mk300.marshal.minimum.test.pojo.NoDefaultConstructorAndFinalField;


public class NoDefaultConstructorAndFinalFieldTest {

	@Test
	public void noDefaultConstructorAndFinalFieldTest() throws Exception {
		NoDefaultConstructorAndFinalField pojo = new NoDefaultConstructorAndFinalField("hoge1", "hoge2");
				
		TestUtil.testAndPrintHexAndCheck(pojo);
	}
	

}
