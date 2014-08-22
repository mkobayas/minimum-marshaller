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
import java.util.concurrent.ConcurrentSkipListSet;

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
public class ConcurrentSkipListSetHandler implements MarshalHandler<ConcurrentSkipListSet> {

	@Override
	public void writeObject(OOutput out, ConcurrentSkipListSet set) throws IOException {
		out.writeObject(set.comparator());
		NaturalNumberIoHelper.writeNaturalNumber(out, set.size());
		for(Object element : set) {
			out.writeObject(element);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ConcurrentSkipListSet readObject(OInput in, Class<ConcurrentSkipListSet> clazz) throws IOException {
		Comparator comparator = (Comparator)in.readObject();
		ConcurrentSkipListSet set = new ConcurrentSkipListSet(comparator);
		
		int size = NaturalNumberIoHelper.readNaturalNumber(in);
		for(int i=0 ; i<size ; i++) {
			set.add(in.readObject());
		}
		
		return set;
	}

}
