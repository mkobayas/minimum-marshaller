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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.mk300.marshal.minimum.MarshalHandler;
import org.mk300.marshal.minimum.io.NaturalNumberIoHelper;
import org.mk300.marshal.minimum.io.OInputStream;
import org.mk300.marshal.minimum.io.OOutputStream2;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
@SuppressWarnings("rawtypes")
public class LinkedHashMapHandler implements MarshalHandler<LinkedHashMap> {

	private static final Field fLoadFactor;
	static {
		try {
			fLoadFactor = HashMap.class.getDeclaredField("loadFactor");
			fLoadFactor.setAccessible(true);
		} catch (Exception e) {
			throw new Error(e);
		}
	}

	private static final Field fAccessOrder;
	static {
		try {
			fAccessOrder = LinkedHashMap.class.getDeclaredField("accessOrder");
			fAccessOrder.setAccessible(true);
		} catch (Exception e) {
			throw new Error(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void writeObject(OOutputStream2 out, LinkedHashMap map) throws IOException {
		
		float loadFactor;
		boolean accessOrder;
		try {
			loadFactor = fLoadFactor.getFloat(map);
			accessOrder = fAccessOrder.getBoolean(map);
		} catch (Exception e) {
			throw new IOException(e);
		}

		out.writeFloat(loadFactor);
		out.writeBoolean(accessOrder);
		
		NaturalNumberIoHelper.writeNaturalNumber(out, map.size());
		
		for(Entry element : (Set<Map.Entry>)map.entrySet()) {
			out.writeObject(element.getKey());
			out.writeObject(element.getValue());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public LinkedHashMap readObject(OInputStream in, Class<LinkedHashMap> clazz) throws IOException {
		float loadFactor = in.readFloat();
		boolean accessOrder = in.readBoolean();
		
		LinkedHashMap map = new LinkedHashMap<>(16, loadFactor, accessOrder);
		
		int size = NaturalNumberIoHelper.readNaturalNumber(in);
		
		for(int i=0 ; i<size ; i++) {
			Object key = in.readObject();
			Object value = in.readObject();
			map.put(key, value);
		}
		
		return map;
	}

}
