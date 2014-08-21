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
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

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
public class MapHandler implements MarshalHandler<Map> {

	@SuppressWarnings("unchecked")
	@Override
	public void writeObject(OOutputStream2 out, Map map) throws IOException {
		NaturalNumberIoHelper.writeNaturalNumber(out, map.size());
		
		for(Entry element : (Set<Map.Entry>)map.entrySet()) {
			out.writeObject(element.getKey());
			out.writeObject(element.getValue());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map readObject(OInputStream in, Class<Map> clazz) throws IOException {
		Map map;
		try {
			map = clazz.newInstance();
		} catch (Exception e) {
			throw new IOException("Unable instantiate: " + clazz, e);
		}
		
		int size = NaturalNumberIoHelper.readNaturalNumber(in);
		
		for(int i=0 ; i<size ; i++) {
			Object key = in.readObject();
			Object value = in.readObject();
			map.put(key, value);
		}
		
		return map;
	}

}
