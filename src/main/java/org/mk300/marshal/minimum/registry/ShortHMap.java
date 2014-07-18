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

package org.mk300.marshal.minimum.registry;

import java.util.TreeMap;

import org.mk300.marshal.minimum.MarshalHandler;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
@SuppressWarnings("rawtypes")
public class ShortHMap extends TreeMap<Integer, MarshalHandler> {
	
	private static final long serialVersionUID = 1L;
	
	private boolean dirty = true;
	private MarshalHandler[] array;
	
	public MarshalHandler get(short key) {
		int id = key & 0xFFFF;
		if( dirty ) {
			return super.get(id);
		} else {
			return array[id];
		}
	}
	
	public MarshalHandler remove(short key) {
		dirty = true;
		int id = key & 0xFFFF;
		return super.remove(id);
	}


	public MarshalHandler put(short key, MarshalHandler value) {
		dirty = true;
		int id = key & 0xFFFF;
		return super.put(id, value);
	}
	
	public void fix() {

		int lastId = lastEntry().getKey() + 1;
		array = new MarshalHandler[lastId];
		
		for(java.util.Map.Entry<Integer, MarshalHandler> entry : entrySet()) {
			int key = entry.getKey();
			array[key] = entry.getValue();
		}
		dirty = false;
	}
}
