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

import org.mk300.marshal.minimum.MarshalHandler;
import org.mk300.marshal.minimum.io.NaturalNumberIoHelper;
import org.mk300.marshal.minimum.io.OInputStream;
import org.mk300.marshal.minimum.io.OOutputStream2;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
public class StringArrayHandler implements MarshalHandler<String[]> {

	@Override
	public void writeObject(OOutputStream2 out, String[] array) throws IOException {
		NaturalNumberIoHelper.writeNaturalNumber(out, array.length);
		for(int i=0; i< array.length; i++ ) {
			out.writeString(array[i]);
		}
	}

	@Override
	public String[] readObject(OInputStream in, Class<String[]> clazz) throws IOException {
		int size = NaturalNumberIoHelper.readNaturalNumber(in);
		
		String[] array = new String[size];
		for(int i=0; i<size ; i++) {
			array[i] = in.readString();
		}			
		return array;
	}
}
