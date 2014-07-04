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
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;

import org.mk300.marshal.minimum.MarshalHandler;
import org.mk300.marshal.minimum.io.NaturalNumberIoHelper;
import org.mk300.marshal.minimum.io.OInputStream;
import org.mk300.marshal.minimum.io.OOutputStream;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class ConcurrentSkipListMapHandler implements MarshalHandler<ConcurrentSkipListMap> {

	@Override
	public void writeObject(OOutputStream out, ConcurrentSkipListMap map) throws IOException {
		out.writeObject(map.comparator());
		NaturalNumberIoHelper.writeNaturalNumber(out, map.size());
		for(Entry element : (Set<Map.Entry>)map.entrySet()) {
			out.writeObject(element.getKey());
			out.writeObject(element.getValue());
		}
	}

	@Override
	public ConcurrentSkipListMap readObject(OInputStream in, Class<ConcurrentSkipListMap> clazz) throws IOException {
		Comparator comparator = (Comparator)in.readObject();
		ConcurrentSkipListMap map = new ConcurrentSkipListMap(comparator);
		
		int size = NaturalNumberIoHelper.readNaturalNumber(in);
		for(int i=0 ; i<size ; i++) {
			Object key = in.readObject();
			Object value = in.readObject();
			map.put(key, value);
		}
		
		return map;
	}

}
