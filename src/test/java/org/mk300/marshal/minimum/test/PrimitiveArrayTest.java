package org.mk300.marshal.minimum.test;

import org.junit.Test;

public class PrimitiveArrayTest {

	@Test
	public void testByteArray() throws Exception {
		TestUtil.testAndPrintHexAndCheck(new byte[]{1,2,3});		
	}
	

	@Test
	public void testShortArray() throws Exception {
		TestUtil.testAndPrintHexAndCheck(new short[]{1,2,3});		
	}
	

	@Test
	public void testIntArray() throws Exception {
		TestUtil.testAndPrintHexAndCheck(new int[]{1,2,3});		
	}
	

	@Test
	public void testLongArray() throws Exception {
		TestUtil.testAndPrintHexAndCheck(new long[]{1,2,3});		
	}
	

	@Test
	public void testBooleanArray() throws Exception {
		TestUtil.testAndPrintHexAndCheck(new boolean[]{true, false, true});		
	}
	

	@Test
	public void testFloatArray() throws Exception {
		TestUtil.testAndPrintHexAndCheck(new float[]{1.1f,2.2f,3.3f});		
	}
	

	@Test
	public void testDoubleArray() throws Exception {
		TestUtil.testAndPrintHexAndCheck(new double[]{1.1,2.2,3.3});		
	}
	

	@Test
	public void testCharArray() throws Exception {
		TestUtil.testAndPrintHexAndCheck(new char[]{'a','b','c'});		
	}

}
