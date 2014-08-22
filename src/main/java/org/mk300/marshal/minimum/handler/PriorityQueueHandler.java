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
import java.util.Comparator;
import java.util.PriorityQueue;

import org.mk300.marshal.minimum.MarshalHandler;
import org.mk300.marshal.minimum.io.NaturalNumberIoHelper;
import org.mk300.marshal.minimum.io.OInput;
import org.mk300.marshal.minimum.io.OOutput;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
@SuppressWarnings("rawtypes")
public class PriorityQueueHandler implements MarshalHandler<PriorityQueue> {

	@Override
	public void writeObject(OOutput out, PriorityQueue queue) throws IOException {
		NaturalNumberIoHelper.writeNaturalNumber(out, queue.size());
		out.writeObject(queue.comparator());
		for(Object element : queue) {
			out.writeObject(element);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public PriorityQueue readObject(OInput in, Class<PriorityQueue> clazz) throws IOException {
		int size = NaturalNumberIoHelper.readNaturalNumber(in);
		
		Comparator comparator = (Comparator)in.readObject();
		PriorityQueue queue = new PriorityQueue(size, comparator);
		
		for(int i=0 ; i<size ; i++) {
			queue.add(in.readObject());
		}
		
		return queue;
	}

}
