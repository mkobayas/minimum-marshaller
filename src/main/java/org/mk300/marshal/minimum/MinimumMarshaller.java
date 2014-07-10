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

package org.mk300.marshal.minimum;


import java.io.IOException;

import org.mk300.marshal.minimum.io.BAInputStream;
import org.mk300.marshal.minimum.io.BAOutputStream;
import org.mk300.marshal.minimum.io.OInputStream;
import org.mk300.marshal.minimum.io.OOutputStream;


/**
 *  
 * @author mkobayas@redhat.com
 *
 */
public class MinimumMarshaller {
	
	public static byte[] marshal(Object obj, int estimatedSize) throws IOException {
		BAOutputStream baos = new BAOutputStream(estimatedSize);
		OOutputStream oos = new OOutputStream(baos);
		oos.writeObject(obj);
		return baos.toByteArray();
	}

	public static byte[] marshal(Object obj) throws IOException {
		return marshal(obj, 32);
	}

	public static Object unmarshal(byte[] buf) throws IOException {
		BAInputStream bais = new BAInputStream(buf);		
		OInputStream ois = new OInputStream(bais);
		return ois.readObject();
	}
}
