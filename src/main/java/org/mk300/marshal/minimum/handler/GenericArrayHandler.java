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
import java.lang.reflect.Array;

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
public class GenericArrayHandler implements MarshalHandler {
	
	@Override
	public void writeObject(OOutput out, Object o) throws IOException {
		int len = Array.getLength(o);
		NaturalNumberIoHelper.writeNaturalNumber(out, len);
		for(int i=0; i<len ; i++) {
			out.writeObject(Array.get(o, i));
		}
	}

	@Override
	public Object readObject(OInput in, Class clazz)
			throws IOException {
		int size = NaturalNumberIoHelper.readNaturalNumber(in);
		Object o = Array.newInstance(clazz.getComponentType(), size);
		for(int i=0; i<size; i++) {
			Object element = in.readObject();
			Array.set(o, i, element);
		}
		return o;
	}

}
