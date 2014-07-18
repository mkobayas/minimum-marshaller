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

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
@SuppressWarnings("rawtypes")
public class ShortCMap extends TreeMap<Integer, Class> {
	
	private static final long serialVersionUID = 1L;
	
	private boolean dirty = true;
	private Class[] array;
	
	public Class get(short key) {
		int id = key & 0xFFFF;
		if( dirty ) {
			return super.get(id);
		} else {
			return array[id];
		}
	}
	
	public Class remove(short key) {
		dirty = true;
		int id = key & 0xFFFF;
		return super.remove(id);
	}


	public Class put(short key, Class value) {
		dirty = true;
		int id = key & 0xFFFF;
		return super.put(id, value);
	}
	
	public void fix() {

		int lastId = lastEntry().getKey() + 1;
		array = new Class[lastId];
		
		for(java.util.Map.Entry<Integer, Class> entry : entrySet()) {
			int key = entry.getKey();
			array[key] = entry.getValue();
		}
		dirty = false;
	}
}
