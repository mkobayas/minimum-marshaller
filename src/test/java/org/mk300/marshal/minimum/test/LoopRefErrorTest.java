package org.mk300.marshal.minimum.test;

import java.util.ArrayList;

import org.junit.Test;
import org.mk300.marshal.common.InfiniteLoopException;
import org.mk300.marshal.minimum.MinimumMarshaller;
import org.mk300.marshal.minimum.test.pojo.SampleData2;

public class LoopRefErrorTest {

	@Test(expected = InfiniteLoopException.class)
	public void testNormalHandler() throws Exception {
		SampleData2 s1 = new SampleData2();
		SampleData2 s2 = new SampleData2();
		
		s1.setP4(s2);
		s2.setP4(s1);
		
		MinimumMarshaller.marshal(s1);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test(expected = InfiniteLoopException.class)
	public void testCustomHandler() throws Exception {
		ArrayList s1 = new ArrayList<>();
		ArrayList s2 = new ArrayList<>();
		
		s1.add(s2);
		s2.add(s1);
		
		MinimumMarshaller.marshal(s1);
	}
	
}
