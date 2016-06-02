package org.mk300.marshal.minimum.test;

import java.util.Stack;

import org.junit.Test;
import org.mk300.marshal.minimum.test.pojo.SampleData;

public class StackTest {
	
	@Test
	public void testStack() throws Exception {
		Stack<SampleData> stack = new Stack<SampleData>();
		
		for (int i=0; i< 5; i++) {
			SampleData o = new SampleData();
			o.setP1("" + i);
			stack.add(o);
		}
		
		TestUtil.testAndPrintHexAndCheck(stack);
	}
}
