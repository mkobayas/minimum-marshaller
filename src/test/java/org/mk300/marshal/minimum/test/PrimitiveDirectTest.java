package org.mk300.marshal.minimum.test;

import org.junit.Test;

public class PrimitiveDirectTest {

	@Test
	public void testByte() throws Exception {
		byte data = 1;
		TestUtil.testAndPrintHexAndCheck(data);
	}
	

	@Test
	public void testShort() throws Exception {
		short data = 1;
		TestUtil.testAndPrintHexAndCheck(data);
	}
	

	@Test
	public void testInt() throws Exception {
		int data = 1;
		TestUtil.testAndPrintHexAndCheck(data);
	}
	

	@Test
	public void testLong() throws Exception {
		long data = 1;
		TestUtil.testAndPrintHexAndCheck(data);
	}
	

	@Test
	public void testBoolean() throws Exception {
		boolean data = true;
		TestUtil.testAndPrintHexAndCheck(data);
	}
	

	@Test
	public void testFloat() throws Exception {
		float data = 1.2f;
		TestUtil.testAndPrintHexAndCheck(data);
	}
	

	@Test
	public void testDouble() throws Exception {
		double data = 1.2;
		TestUtil.testAndPrintHexAndCheck(data);
	}
	

	@Test
	public void testCharArray() throws Exception {
		char data = 'a';
		TestUtil.testAndPrintHexAndCheck(data);
	}
}
