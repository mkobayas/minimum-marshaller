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

package org.mk300.marshal.common;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
public class UnsafeClassMetaDataRegistry {
	

	/**
	 * プロパティを書き出す/読み込む順番をキャッシュする。<br>
	 *  プロパティを書き出す/読み込む順番の解決はコストが掛かる処理なのでキャッシュを実施。
	 */
	private static ConcurrentHashMap<Class<?>, UnsafeFieldAccessor[]> fieldOrderCache = new ConcurrentHashMap<Class<?>, UnsafeFieldAccessor[]>();

	/**
	 * 指定されたオブジェクトのクラスから、永続化プロパティのリストを作成する。<br>
	 * 永続化プロパティのリストは以下の順序付けが行わる<br>
	 * <ol>
	 * <li>はじめに、{@link ExternOrder}アノテーションが付与されていないプロパティ(継承元も含めて収集)を{@link Field#toString()}の昇順で並べる。</li>
	 * <li>次に、{@link ExternOrder}アノテーションが付与されているプロパティ(継承元も含めて収集)を{@link ExternOrder#value()}の値で昇順に並べる。</li>
	 * <li>最後に上記の２つのリストを結合してプロパティリストを作成する。</li>
	 * </ol>
	 * 
	 * 本メソッド内で、{@link Field#setAccessible(boolean)}を適宜呼び出している。<br>
	 * また、transient、又はstaticで修飾されたプロパティはリストから除外されている。<br>
	 * 
	 * @param clazz
	 * @return
	 */
	public static UnsafeFieldAccessor[] getFieldList(Class<?> clazz) {
		UnsafeFieldAccessor[] fields = null;
		// キャッシュヒットした場合はキャッシュを利用
		fields = fieldOrderCache.get(clazz);
		
		if( fields != null ) {
			return fields;
		}

		List<Field> fieldList = null;
		Class<?> searchClazz = clazz;

		List<Field> orderedFieldList = new ArrayList<Field>();
		List<Field> nonOrderedFieldList = new ArrayList<Field>();
		fieldList = new ArrayList<Field>();
		
		while( ! searchClazz.equals(Object.class) ) {
			// privateを含む全てのフィールドを取得
			Field[] fieldArray = searchClazz.getDeclaredFields();

			// transient/static以外 & Annotatedな項目を取得
			for (Field f : fieldArray) {
				if ((f.getModifiers() & Modifier.TRANSIENT) != 0 || (f.getModifiers() & Modifier.STATIC) != 0) {
					continue;
				}
	
				if (f.isAnnotationPresent(ExternOrder.class)) {
					if (f.isAccessible() == false) {
						f.setAccessible(true);
					}
					orderedFieldList.add(f);
				} else {
					if (f.isAccessible() == false) {
						f.setAccessible(true);
					}
					nonOrderedFieldList.add(f);
				}
			}
			
			searchClazz = searchClazz.getSuperclass();
		}

		Collections.sort(nonOrderedFieldList, new ExternAlphabetSortComparator());
		Collections.sort(orderedFieldList, new ExternOrderSortComparator());

		fieldList.addAll(nonOrderedFieldList);
		fieldList.addAll(orderedFieldList);
		
		fields = UnsafeFieldAccessor.convert(fieldList.toArray(new Field[0]));
		fieldOrderCache.put(clazz, fields );
		
		return fields;
	}
	

	
	private static class ExternAlphabetSortComparator implements Comparator<Field> {

		@Override
		public int compare(Field o1, Field o2) {
			String n1 = o1.toString();
			String n2 = o2.toString();
			
			return  n1.compareTo(n2);
		}
	}
	
	private static class ExternOrderSortComparator implements Comparator<Field> {

		@Override
		public int compare(Field o1, Field o2) {

			ExternOrder eo1 = o1.getAnnotation(ExternOrder.class);
			ExternOrder eo2 = o2.getAnnotation(ExternOrder.class);
			
			if( eo1.value() - eo2.value() != 0) {
				return eo1.value() - eo2.value();
			} else {
				
				// 同じオーダ番号の場合は、Field.toString()の順序
				String n1 = o1.toString();
				String n2 = o2.toString();
				
				return  n1.compareTo(n2);
			}
		}	
	}
}
