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
import java.util.TreeSet;

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
public class TreeSetHandler implements MarshalHandler<TreeSet> {

	@Override
	public void writeObject(OOutput out, TreeSet treeSet) throws IOException {
		out.writeObject(treeSet.comparator());
		NaturalNumberIoHelper.writeNaturalNumber(out, treeSet.size());
		for(Object element : treeSet) {
			out.writeObject(element);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public TreeSet readObject(OInput in, Class<TreeSet> clazz) throws IOException {
		Comparator comparator = (Comparator)in.readObject();
		TreeSet treeSet = new TreeSet(comparator);
		
		int size = NaturalNumberIoHelper.readNaturalNumber(in);
		for(int i=0 ; i<size ; i++) {
			treeSet.add(in.readObject());
		}
		
		return treeSet;
	}

}
