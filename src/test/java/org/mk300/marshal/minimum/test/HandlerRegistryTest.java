package org.mk300.marshal.minimum.test;

import org.junit.Test;
import static org.junit.Assert.*;

import org.mk300.marshal.minimum.MinimumMarshaller;
import org.mk300.marshal.minimum.registry.HandlerRegistry;
import org.mk300.marshal.minimum.test.pojo.SampleData;

public class HandlerRegistryTest {
	
	@Test
	public void testDefinedClass() throws Exception {
		SampleData data = new SampleData();
		boolean ret = HandlerRegistry.isDefined(data);
		assertTrue(ret);
	}
	
	@Test
	public void testUndefinedClass() throws Exception {
		HandlerRegistry data = new HandlerRegistry();
		boolean ret = HandlerRegistry.isDefined(data);
		assertFalse(ret);
	}
	

	
	@Test
	public void testDefinedClassMM() throws Exception {
		SampleData data = new SampleData();
		boolean ret = MinimumMarshaller.isDefined(data);
		assertTrue(ret);
	}
	
	@Test
	public void testUndefinedClassMM() throws Exception {
		HandlerRegistry data = new HandlerRegistry();
		boolean ret = MinimumMarshaller.isDefined(data);
		assertFalse(ret);
	}
}
