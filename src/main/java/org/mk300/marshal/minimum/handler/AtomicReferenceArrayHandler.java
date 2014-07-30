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
import java.util.concurrent.atomic.AtomicReferenceArray;

import org.mk300.marshal.minimum.MarshalHandler;
import org.mk300.marshal.minimum.io.NaturalNumberIoHelper;
import org.mk300.marshal.minimum.io.OInputStream;
import org.mk300.marshal.minimum.io.OOutputStream;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
@SuppressWarnings("rawtypes")
public class AtomicReferenceArrayHandler implements MarshalHandler<AtomicReferenceArray> {

	@Override
	public void writeObject(OOutputStream out, AtomicReferenceArray array) throws IOException {
		int len = array.length();
		NaturalNumberIoHelper.writeNaturalNumber(out, len);
		for (int i=0; i<len; i++) {
			out.writeObject(array.get(i));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public AtomicReferenceArray readObject(OInputStream in, Class<AtomicReferenceArray> clazz) throws IOException {
		int size = NaturalNumberIoHelper.readNaturalNumber(in);
		AtomicReferenceArray array = new AtomicReferenceArray(size);

		for (int i = 0; i < size; i++) {
			array.set(i, in.readObject());
		}
		
		return array;
	}

}
