package org.mk300.marshal.minimum.test;

import org.junit.Test;

public class GenericArrayTest {
	@Test
	public void test3DimStringArray() throws Exception {
		String[][][] data = new String[][][]{{{"hoge1","hoge2"},{"hoge3","hoge4"}},{{"hoge5","hoge6"},{"hoge7","hoge8"}}};
		TestUtil.testAndPrintHexAndCheck(data);	
	}

	/**
	 * marshaller-configに設定がないUNKNOWNな配列
	 * @throws Exception
	 */
	@Test
	public void test3DimUndefinedArray() throws Exception {
		Long[][][] data = new Long[][][]{{{1l,2l},{3l,4l}},{{5l,6l},{7l,8l}}};
		TestUtil.testAndPrintHexAndCheck(data);	
	}
	/**
	 * marshaller-configに設定がないUNKNOWNな配列
	 * @throws Exception
	 */
	@Test
	public void test1DimUndefinedArray() throws Exception {
		Long[] data = new Long[]{1l,2l};
		TestUtil.testAndPrintHexAndCheck(data);	
	}
	
	@Test
	public void testTwoDimByteArray() throws Exception {
		TestUtil.testAndPrintHexAndCheck(new byte[][]{{1,2,3},{4,5,6,7}});		
	}
}
