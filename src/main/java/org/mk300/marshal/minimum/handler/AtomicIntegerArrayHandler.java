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
import java.util.concurrent.atomic.AtomicIntegerArray;

import org.mk300.marshal.minimum.MarshalHandler;
import org.mk300.marshal.minimum.io.NaturalNumberIoHelper;
import org.mk300.marshal.minimum.io.OInputStream;
import org.mk300.marshal.minimum.io.OOutputStream2;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
public class AtomicIntegerArrayHandler implements MarshalHandler<AtomicIntegerArray> {

	@Override
	public void writeObject(OOutputStream2 out, AtomicIntegerArray array) throws IOException {
		int len = array.length();
		NaturalNumberIoHelper.writeNaturalNumber(out, len);
		for (int i=0; i<len; i++) {
			out.writeInt(array.get(i));
		}
	}

	@Override
	public AtomicIntegerArray readObject(OInputStream in, Class<AtomicIntegerArray> clazz) throws IOException {
		int size = NaturalNumberIoHelper.readNaturalNumber(in);
		AtomicIntegerArray array = new AtomicIntegerArray(size);

		for (int i = 0; i < size; i++) {
			array.set(i, in.readInt());
		}
		
		return array;
	}

}
