package org.mk300.marshal.minimum.test;

import org.junit.Test;
import org.mk300.marshal.minimum.io.OOutputImpl;

public class OOutputImplBufferSizeTest {


	@Test
	public void testBufferSize() throws Exception {
				
		OOutputImpl out = new OOutputImpl(32);
		
		for(int i=0; i<150; i++) {
			out.writeUTF("a");
		}
	}
}
