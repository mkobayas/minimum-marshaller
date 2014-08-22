/*
 * Copyright 2014 Masazumi Kobayashi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mk300.marshal.minimum.handler;


import java.io.IOException;
import java.lang.reflect.Field;
import java.util.concurrent.SynchronousQueue;

import org.mk300.marshal.minimum.MarshalHandler;
import org.mk300.marshal.minimum.io.OInput;
import org.mk300.marshal.minimum.io.OOutput;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
@SuppressWarnings("rawtypes")
public class SynchronousQueueHandler implements MarshalHandler<SynchronousQueue> {

	private static final Field f;
	static {
		try {
			f = SynchronousQueue.class.getDeclaredField("transferer");
			f.setAccessible(true);
		} catch (Exception e) {
			throw new Error(e);
		}
	}
	
	@Override
	public void writeObject(OOutput out, SynchronousQueue queue) throws IOException {
		boolean fair;
		try {
			fair = f.get(queue).getClass().getName().equals("java.util.concurrent.SynchronousQueue$TransferQueue");
			System.out.println(f.get(queue).getClass().getName());
		} catch (Exception e) {
			throw new IOException(e);
		}
		out.writeBoolean(fair);	
	}

	@Override
	public SynchronousQueue readObject(OInput in, Class<SynchronousQueue> clazz) throws IOException {
		boolean fair = in.readBoolean();
		SynchronousQueue queue = new SynchronousQueue(fair);
		return queue;
	}
}
