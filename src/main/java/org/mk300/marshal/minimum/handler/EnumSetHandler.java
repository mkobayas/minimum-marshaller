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
import java.util.EnumSet;

import org.mk300.marshal.minimum.MarshalHandler;
import org.mk300.marshal.minimum.io.NaturalNumberIoHelper;
import org.mk300.marshal.minimum.io.OInputStream;
import org.mk300.marshal.minimum.io.OOutputStream2;
import org.mk300.marshal.minimum.registry.HandlerRegistry;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class EnumSetHandler implements MarshalHandler<EnumSet> {
	private static final Field f;
	static {
		try {
			f = EnumSet.class.getDeclaredField("elementType");
			f.setAccessible(true);
		} catch (Exception e) {
			throw new Error(e);
		}
	}
	
	@Override
	public void writeObject(OOutputStream2 out, EnumSet set) throws IOException {
		Class type;
		try {
			type = (Class)f.get(set);
		} catch (Exception e) {
			throw new IOException(e);
		}

		
		// element type
		short id = HandlerRegistry.getClassId(type);
		out.writeShort(id);
//		out.writeString(type.getName());
		

		// element size
		NaturalNumberIoHelper.writeNaturalNumber(out, set.size());
		
		// each element
		for(Object e : set) {
			Enum en = (Enum)e;
			NaturalNumberIoHelper.writeNaturalNumber(out, en.ordinal());
		}
	}

	@Override
	public EnumSet readObject(OInputStream in, Class<EnumSet> clazz) throws IOException {
		
		
		// element type
		short id = in.readShort();
		Class<Enum> enumType = (Class<Enum>)HandlerRegistry.getObjClass(id);
//		String typeStr = in.readString(); 
//		Class<Enum> enumType;
//		try {
//			enumType = (Class<Enum>)Class.forName(typeStr);
//		} catch (ClassNotFoundException e1) {
//			throw new IOException(e1);
//		}
		
		EnumSet set = EnumSet.noneOf(enumType);

		// element size
		int size = NaturalNumberIoHelper.readNaturalNumber(in);
		
		// each element
		for(int i=0; i<size; i++) {
			int o = NaturalNumberIoHelper.readNaturalNumber(in);
			Enum e = enumType.getEnumConstants()[o];
			set.add(e);
		}
		
		return set;
	}

}
