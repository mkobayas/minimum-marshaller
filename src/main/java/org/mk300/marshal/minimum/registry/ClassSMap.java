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



/**
 * This is experimental implementation for investigate perfomance improvement.
 * https://github.com/mkobayas/minimum-marshaller/issues/35
 * 
 * @author mkobayas@redhat.com
 *
 */
@Deprecated
@SuppressWarnings("rawtypes")
public class ClassSMap {

	Class[][] keyTable;
	short[][] valueTable;
	
	public ClassSMap() {
		keyTable = new Class[10][3];
		valueTable = new short[10][3];
	}
	
	public short get(Class clazz) {
		
		int tableIndex = clazz.hashCode() % keyTable.length;

		Class[] keyArray = keyTable[tableIndex];
		int index = 0;
		for(; index < keyArray.length; index++) {
			if(keyArray[index] == clazz) {
				return valueTable[tableIndex][index];
			}
		}
		
		return -1;
	}
	
	
	public short put(Class clazz, short id) {
		short old = -1;
		int tableIndex = clazz.hashCode() % keyTable.length;

		Class[] keyArray = keyTable[tableIndex];
		int index = 0;
		for(; index < keyArray.length; index++) {
			if(keyArray[index] == clazz) {
				old = valueTable[tableIndex][index];
				break;
			} else if(keyArray[index] == null) {
				break;
			}
		}
		
		if(keyArray.length == index ) {
			rehash();
			return put(clazz, id);
		} else {
			
			keyArray[index] = clazz;
			valueTable[tableIndex][index] = id;
			
			return old;
		}
	}
	
	
	private void rehash() {
		int size = keyTable.length;
		
		Class[][] newKeyTable;
		short[][] newValueTable;
		
		while(true) {
			boolean fail = false;
			size += 10;
			
			newKeyTable = new Class[size][3];
			newValueTable = new short[size][3];
			
			for(int i=0; i<keyTable.length ; i++) {
				int j =0;
				for(; j<keyTable[i].length; j++) {
					if(keyTable[i][j] != null) {
						Class clazz = keyTable[i][j];
						short id = valueTable[i][j];
	
						int tableIndex = clazz.hashCode() % newKeyTable.length;
						Class[] keyArray = newKeyTable[tableIndex];
						int index = 0;
						boolean success = false;
						for(; index < keyArray.length; index++) {
							if(keyArray[index] == null) {
								success = true;
								break;
							}
						}

						if( !success ) {
							fail = true;
							break;
						}
						
						keyArray[index] = clazz;
						newValueTable[tableIndex][index] = id;
						
					}
				}

				if(fail) {
					break;
				}
			}
			
			if(fail) {
				continue;
			}
			
			System.out.println("rehash size=" + size);
			break;
		}
		
		keyTable = newKeyTable;
		valueTable = newValueTable;
	}
}
