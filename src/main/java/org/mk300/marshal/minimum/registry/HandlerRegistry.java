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


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mk300.marshal.minimum.MarshalHandler;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
@SuppressWarnings("rawtypes")
public class HandlerRegistry {

	private static ShortHMap handlerMap = new ShortHMap();
	private static Map<Class<?>, Short> classIdRMap = new HashMap<Class<?>, Short>();
	private static ShortCMap classMap = new ShortCMap();

	public static short ID_NULL = 0;
	public static short ID_ENUM = 1;
	public static short ID_UNDEFINED_POJO = 2;
	public static short ID_UNDEFINED_ENUM = 3;
    public static short ID_LAMBDA = 4;
	
	private static boolean allowUndefined = true;
	
	static {
		String systemConfigName = System.getProperty("minimum.system.marshaller-config", "system-marshaller-config.xml");
		readConfig(systemConfigName);

		String userConfigName = System.getProperty("minimum.user.marshaller-config", "marshaller-config.xml");
		readConfig(userConfigName);
		
		handlerMap.fix();
		classMap.fix();
	}

	private static void readConfig(String configName) {
		readConfig(configName, false);
	}
	
	private static void readConfig(String configName, boolean optional) {
		List<ConfigElement> configElements = new ArrayList<ConfigElement>();
		List<AdditinalConfigElement> addtinalConfig = new ArrayList<AdditinalConfigElement>();
		InputStream is = null;
		try {
			ClassLoader cl = HandlerRegistry.class.getClassLoader();
			is = cl.getResourceAsStream(configName);
			if(is == null) {
				cl = Thread.currentThread().getContextClassLoader();
				is = cl.getResourceAsStream(configName);
			}
			
			if( is == null ) {
				// read from file path
				try {
					is = new FileInputStream(configName);
				} catch (FileNotFoundException e) {
					
					if( optional ) {
						return;
					} else {
						throw new RuntimeException("指定された設定ファイルをパスから取得できません. configName=" + configName);
					}
				}
			}
			
			ConfigReader reader = new ConfigReader();
			addtinalConfig = reader.read(is, configElements);
		} finally {
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
		
		for(ConfigElement mapping : configElements) {
			
			try {
				short id = mapping.getId();
				Class<?> targetClass = classLoad(mapping.getClassName());
				Class<?> handlerClass = classLoad(mapping.getHandlerName());
				MarshalHandler handler = (MarshalHandler)handlerClass.newInstance();
				regitster(id, targetClass, handler);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		for(AdditinalConfigElement config : addtinalConfig) {
			readConfig(config.getPath(), config.isOptional());
		}
	}
		
	private static void regitster(short id, Class<?> clazz, MarshalHandler m) {
		Class<?> old = classMap.put(id, clazz);
		if( old != null) {
			throw new RuntimeException("1つのidに対して、複数のクラスが指定されています(idの重複). id=" + id + ", class1=" + clazz + ", class2=" + old);
		}
		Short oldId = classIdRMap.put(clazz, id);
		if( oldId != null) {
			throw new RuntimeException("1つのクラスに対して、複数のidが指定されています(classの重複). class=" + clazz + ", id1=" + id + ", id2=" + oldId);
		}
		
		handlerMap.put(id, m);
	}
	
	public static MarshalHandler getMarshallHandler(short id) {
		MarshalHandler m = handlerMap.get(id);

		if( m == null) {
			throw new RuntimeException("MarshallHandlerが設定されていません。id=" + id);
		}
		
		return m;
	}
	
	public static short getClassId(Class<?> clazz) {
		Short id = classIdRMap.get(clazz);
		
		if( id == null) {
			if(allowUndefined) {
				if( clazz.isEnum()) {
					return ID_UNDEFINED_ENUM;
				} else {
					return ID_UNDEFINED_POJO;
				}
			} else {
				throw new RuntimeException("classのidが設定されていません。clazz=" + clazz);
			}
		}
		
		return id;
	}

	public static boolean isDefined(Object pojo) {
		if(pojo == null) {
			return true;
		}
		Short id = classIdRMap.get(pojo.getClass());
		return id == null ? false : true ;
	}
	
	public static boolean isDefined(Class<?> clazz) {
		Short id = classIdRMap.get(clazz);
		return id == null ? false : true ;
	}
	
	
	public static Class<?> getObjClass(short id) {
		Class<?> clazz = classMap.get(id);
		
		if( clazz == null) {
			throw new RuntimeException("classが設定されていません。id=" + id);
		}
		
		return clazz;
	}
	
	private static Class classLoad(String className) throws ClassNotFoundException {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			return Class.forName(className, true, Thread.currentThread().getContextClassLoader());
		}
	}
}
