package org.mk300.marshal.minimum.test;


import org.junit.Test;
import org.mk300.marshal.minimum.test.pojo.PrimitiveWrapperData;


public class PrimitiveWrapperTest {

	@Test
	public void nullTest() throws Exception {
		PrimitiveWrapperData p = new PrimitiveWrapperData();
		TestUtil.testAndPrintHexAndCheck(p);

	}
	

	@Test
	public void nonNullTest() throws Exception {
		PrimitiveWrapperData p = new PrimitiveWrapperData();

		p.setBooleanValue(true);
		p.setByteValue((byte)1);
		p.setCharValue('h');
		p.setDoubleValue(111.22);
		p.setFloatValue(11.333f);
		p.setIntValue(987);
		p.setLongValue(Long.MAX_VALUE);
		p.setShortValue(Short.MAX_VALUE);
		
		TestUtil.testAndPrintHexAndCheck(p);
	}
	
	

	@Test
	public void testByte() throws Exception {
		Byte data = new Byte( (byte)1);
		TestUtil.testAndPrintHexAndCheck(data);
	}
	

	@Test
	public void testShort() throws Exception {
		Short data = new Short( (short)1);
		TestUtil.testAndPrintHexAndCheck(data);
	}
	

	@Test
	public void testInt() throws Exception {
		Integer data = new Integer(1);
		TestUtil.testAndPrintHexAndCheck(data);
	}
	

	@Test
	public void testLong() throws Exception {
		Long data = new Long(1l);
		TestUtil.testAndPrintHexAndCheck(data);
	}
	

	@Test
	public void testBoolean() throws Exception {
		Boolean data = new Boolean(true);
		TestUtil.testAndPrintHexAndCheck(data);
	}
	

	@Test
	public void testFloat() throws Exception {
		Float data = new Float(1.2f);
		TestUtil.testAndPrintHexAndCheck(data);
	}
	

	@Test
	public void testDouble() throws Exception {
		Double data = new Double(1.2);
		TestUtil.testAndPrintHexAndCheck(data);
	}
	

	@Test
	public void testCharArray() throws Exception {
		Character data = new Character('a');
		TestUtil.testAndPrintHexAndCheck(data);
	}
}
