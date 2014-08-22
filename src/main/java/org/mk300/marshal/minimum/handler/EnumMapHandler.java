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
import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.mk300.marshal.minimum.MarshalHandler;
import org.mk300.marshal.minimum.io.NaturalNumberIoHelper;
import org.mk300.marshal.minimum.io.OInput;
import org.mk300.marshal.minimum.io.OOutput;
import org.mk300.marshal.minimum.registry.HandlerRegistry;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
@SuppressWarnings("rawtypes")
public class EnumMapHandler implements MarshalHandler<EnumMap> {
	private static final Field f;
	static {
		try {
			f = EnumMap.class.getDeclaredField("keyType");
			f.setAccessible(true);
		} catch (Exception e) {
			throw new Error(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void writeObject(OOutput out, EnumMap map) throws IOException {
		Class type;
		try {
			type = (Class)f.get(map);
		} catch (Exception e) {
			throw new IOException(e);
		}
		
		// key type
		short id = HandlerRegistry.getClassId(type);
		out.writeShort(id);
		
		// entry size
		NaturalNumberIoHelper.writeNaturalNumber(out, map.size());
		
		for(Entry element : (Set<Map.Entry>)map.entrySet()) {
			Enum en = (Enum)element.getKey();
			NaturalNumberIoHelper.writeNaturalNumber(out, en.ordinal());
			
			out.writeObject(element.getValue());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public EnumMap readObject(OInput in, Class<EnumMap> clazz) throws IOException {

		// key type
		short id = in.readShort();
		Class<Enum> enumType = (Class<Enum>)HandlerRegistry.getObjClass(id);
		
		EnumMap map = new EnumMap(enumType);

		// entry size
		int size = NaturalNumberIoHelper.readNaturalNumber(in);
		
		for(int i=0 ; i<size ; i++) {
			int o = NaturalNumberIoHelper.readNaturalNumber(in);
			Enum e = enumType.getEnumConstants()[o];
			
			Object value = in.readObject();
			map.put(e, value);
		}
		
		return map;
	}

}
