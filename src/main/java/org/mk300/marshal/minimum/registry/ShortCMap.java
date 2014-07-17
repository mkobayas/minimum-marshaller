package org.mk300.marshal.minimum.registry;

import java.util.TreeMap;

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
