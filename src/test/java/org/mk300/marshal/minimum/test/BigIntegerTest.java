package org.mk300.marshal.minimum.test;

import java.math.BigInteger;

import org.junit.Test;

public class BigIntegerTest {

	@Test
	public void testBigInteger() throws Exception {
		BigInteger data = new BigInteger("9999999999999999999999999999999999999999999999999999");
		TestUtil.testAndPrintHexAndCheck(data);
	}
	

	@Test
	public void testBigIntegerNegative() throws Exception {
		BigInteger data = new BigInteger("-9999999999999999999999999999999999999999999999999999");
		TestUtil.testAndPrintHexAndCheck(data);
	}
	
}
