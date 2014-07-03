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
import java.util.concurrent.atomic.AtomicLong;

import org.mk300.marshal.minimum.MarshalHandler;
import org.mk300.marshal.minimum.io.OInputStream;
import org.mk300.marshal.minimum.io.OOutputStream;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
public class AtomicLongHandler implements MarshalHandler {

	@Override
	public void writeObject(OOutputStream out, Object o) throws IOException {
		AtomicLong number = (AtomicLong)o;		
		out.writeLong(number.get());	
	}

	@Override
	public Object readObject(OInputStream in, Class<?> clazz) throws IOException {
		long num = in.readLong();
		AtomicLong number = new AtomicLong(num);
		return number;
	}
}
