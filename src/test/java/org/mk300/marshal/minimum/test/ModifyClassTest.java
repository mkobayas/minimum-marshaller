package org.mk300.marshal.minimum.test;


import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.io.HexDump;
import org.junit.Assert;
import org.junit.Test;
import org.mk300.marshal.minimum.MinimumMarshaller;
import org.mk300.marshal.minimum.handler.ObjectHandler;
import org.mk300.marshal.minimum.registry.HandlerRegistry;
import org.mk300.marshal.minimum.registry.ShortCMap;
import org.mk300.marshal.minimum.registry.ShortHMap;

import test.beans.NewClassModifyBean;
import test.beans.OldClassModifyBean;
import test.beans.ParentBean;

@SuppressWarnings({"unchecked"})
public class ModifyClassTest {

	static short classId = (short)29999;
	static short parentClassId = (short)29998;
	
	@Test
	public void testOldToNew() throws Exception {
		// HandlerRegistryの内容を強制的に書き換えて、別のクラス定義で読み込む。
		//　MinimumMarshallerは、IDのみバイナリに書き込む為、HandlerRegistryの内容を書き換えれば別のクラスで復元する。
		// テスト以外では禁止です(HandlerRegistryの内部マップはスレッドセーフでは有りません)。
		
		
		Field f_handlerMap = HandlerRegistry.class.getDeclaredField("handlerMap");
		f_handlerMap.setAccessible(true);
		ShortHMap handlerMap = (ShortHMap)f_handlerMap.get(null);

		Field f_classIdRMap = HandlerRegistry.class.getDeclaredField("classIdRMap");
		f_classIdRMap.setAccessible(true);
		Map<Class<?>, Short> classIdRMap = (Map<Class<?>, Short>)f_classIdRMap.get(null);

		Field f_classMap = HandlerRegistry.class.getDeclaredField("classMap");
		f_classMap.setAccessible(true);
		ShortCMap classMap = (ShortCMap)f_classMap.get(null);
		
		
		// クラスマッピングの準備
		handlerMap.put(classId , new ObjectHandler());
		classIdRMap.put(OldClassModifyBean.class, classId);
		classMap.put(classId, OldClassModifyBean.class);
		
		
		
		
		OldClassModifyBean old = new OldClassModifyBean();
		old.setProp1("prop1prop1");
		
		// 古いオブジェクトでマーシャル
		byte[] bytes = MinimumMarshaller.marshal(old);
		

		// 
		System.out.println(old.getClass().getSimpleName() + " binary size is " + bytes.length);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		HexDump.dump(bytes, 0, os, 0);
		System.out.println(os.toString());
		System.out.println("");

		// マッピングの強制変更
		classIdRMap.remove(OldClassModifyBean.class);
		classIdRMap.put(NewClassModifyBean.class, classId);
		classMap.put(classId, NewClassModifyBean.class);
		

		// 新しいクラスでアンマーシャル
		NewClassModifyBean newBean = (NewClassModifyBean) MinimumMarshaller.unmarshal(bytes);

		// 内容チェック
		Assert.assertEquals("prop1prop1", newBean.getProp1());
		Assert.assertNull(newBean.getProp2());
		
		// 後片付け
		handlerMap.remove(classId);
		classIdRMap.remove(NewClassModifyBean.class);
		classMap.remove(classId);
	}

	
	@Test
	public void testNewToOld() throws Exception {
		// HandlerRegistryの内容を強制的に書き換えて、別のクラス定義で読み込む。
		//　MinimumMarshallerは、IDのみバイナリに書き込む為、HandlerRegistryの内容を書き換えれば別のクラスで復元する。
		// テスト以外では禁止です(HandlerRegistryの内部マップはスレッドセーフでは有りません)。
		
		
		Field f_handlerMap = HandlerRegistry.class.getDeclaredField("handlerMap");
		f_handlerMap.setAccessible(true);
		ShortHMap handlerMap = (ShortHMap)f_handlerMap.get(null);

		Field f_classIdRMap = HandlerRegistry.class.getDeclaredField("classIdRMap");
		f_classIdRMap.setAccessible(true);
		Map<Class<?>, Short> classIdRMap = (Map<Class<?>, Short>)f_classIdRMap.get(null);

		Field f_classMap = HandlerRegistry.class.getDeclaredField("classMap");
		f_classMap.setAccessible(true);
		ShortCMap classMap = (ShortCMap)f_classMap.get(null);
		
		
		// クラスマッピングの準備
		handlerMap.put(classId , new ObjectHandler());
		classIdRMap.put(NewClassModifyBean.class, classId);
		classMap.put(classId, NewClassModifyBean.class);
		
		
		
		NewClassModifyBean newBean = new NewClassModifyBean();
		newBean.setProp1("prop1prop1");
		newBean.setProp2("prop2prop2");
		
		// 新しいクラスでマーシャル
		byte[] bytes = MinimumMarshaller.marshal(newBean);
		

		// ログ
		System.out.println(newBean.getClass().getSimpleName() + " binary size is " + bytes.length);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		HexDump.dump(bytes, 0, os, 0);
		System.out.println(os.toString());
		System.out.println("");


		// マッピングの強制変更
		classIdRMap.remove(NewClassModifyBean.class);
		classIdRMap.put(OldClassModifyBean.class, classId);
		classMap.put(classId, OldClassModifyBean.class);
		

		// 古いクラスでアンマーシャル
		OldClassModifyBean oldBean = (OldClassModifyBean) MinimumMarshaller.unmarshal(bytes);

		// 内容チェック
		Assert.assertEquals("prop1prop1", oldBean.getProp1());
		
		// 後片付け
		handlerMap.remove(classId);
		classIdRMap.remove(OldClassModifyBean.class);
		classMap.remove(classId);
		
	}
	
	

	@Test
	public void testNestOldToNew() throws Exception {
		// HandlerRegistryの内容を強制的に書き換えて、別のクラス定義で読み込む。
		//　MinimumMarshallerは、IDのみバイナリに書き込む為、HandlerRegistryの内容を書き換えれば別のクラスで復元する。
		// テスト以外では禁止です(HandlerRegistryの内部マップはスレッドセーフでは有りません)。
		
		
		Field f_handlerMap = HandlerRegistry.class.getDeclaredField("handlerMap");
		f_handlerMap.setAccessible(true);
		ShortHMap handlerMap = (ShortHMap)f_handlerMap.get(null);

		Field f_classIdRMap = HandlerRegistry.class.getDeclaredField("classIdRMap");
		f_classIdRMap.setAccessible(true);
		Map<Class<?>, Short> classIdRMap = (Map<Class<?>, Short>)f_classIdRMap.get(null);

		Field f_classMap = HandlerRegistry.class.getDeclaredField("classMap");
		f_classMap.setAccessible(true);
		ShortCMap classMap = (ShortCMap)f_classMap.get(null);
		
		
		// クラスマッピングの準備
		handlerMap.put(parentClassId , new ObjectHandler());
		classIdRMap.put(ParentBean.class, parentClassId);
		classMap.put(parentClassId, ParentBean.class);
		
		handlerMap.put(classId , new ObjectHandler());
		classIdRMap.put(OldClassModifyBean.class, classId);
		classMap.put(classId, OldClassModifyBean.class);
		
		
		ParentBean old = new ParentBean();
		
		old.setProp1("prop1");
		
		OldClassModifyBean oldNest = new OldClassModifyBean();
		oldNest.setProp1("prop1prop1");
		old.setProp2(oldNest);
		
		old.setProp3("prop3");
		
		// 古いオブジェクトでマーシャル
		byte[] bytes = MinimumMarshaller.marshal(old);
		

		// 
		System.out.println(old.getClass().getSimpleName() + " binary size is " + bytes.length);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		HexDump.dump(bytes, 0, os, 0);
		System.out.println(os.toString());
		System.out.println("");

		// マッピングの強制変更
		classIdRMap.remove(OldClassModifyBean.class);
		classIdRMap.put(NewClassModifyBean.class, classId);
		classMap.put(classId, NewClassModifyBean.class);
		

		// 新しいクラスでアンマーシャル
		ParentBean newBean = (ParentBean) MinimumMarshaller.unmarshal(bytes);

		// 内容チェック
		Assert.assertEquals("prop1", newBean.getProp1());
		NewClassModifyBean nestBean = (NewClassModifyBean)newBean.getProp2();
		Assert.assertEquals("prop1prop1", nestBean.getProp1());
		Assert.assertNull(nestBean.getProp2());
		Assert.assertEquals("prop3", newBean.getProp3());
		
		// 後片付け

		handlerMap.remove(parentClassId);
		classIdRMap.remove(ParentBean.class);
		classMap.remove(parentClassId);
		
		handlerMap.remove(classId);
		classIdRMap.remove(NewClassModifyBean.class);
		classMap.remove(classId);
	}
	
	

	@Test
	public void testNestNewToOld() throws Exception {
		// HandlerRegistryの内容を強制的に書き換えて、別のクラス定義で読み込む。
		//　MinimumMarshallerは、IDのみバイナリに書き込む為、HandlerRegistryの内容を書き換えれば別のクラスで復元する。
		// テスト以外では禁止です(HandlerRegistryの内部マップはスレッドセーフでは有りません)。
		
		
		Field f_handlerMap = HandlerRegistry.class.getDeclaredField("handlerMap");
		f_handlerMap.setAccessible(true);
		ShortHMap handlerMap = (ShortHMap)f_handlerMap.get(null);

		Field f_classIdRMap = HandlerRegistry.class.getDeclaredField("classIdRMap");
		f_classIdRMap.setAccessible(true);
		Map<Class<?>, Short> classIdRMap = (Map<Class<?>, Short>)f_classIdRMap.get(null);

		Field f_classMap = HandlerRegistry.class.getDeclaredField("classMap");
		f_classMap.setAccessible(true);
		ShortCMap classMap = (ShortCMap)f_classMap.get(null);
		
		
		// クラスマッピングの準備
		handlerMap.put(parentClassId , new ObjectHandler());
		classIdRMap.put(ParentBean.class, parentClassId);
		classMap.put(parentClassId, ParentBean.class);
		
		handlerMap.put(classId , new ObjectHandler());
		classIdRMap.put(NewClassModifyBean.class, classId);
		classMap.put(classId, NewClassModifyBean.class);
		
		ParentBean newBean = new ParentBean();
		
		newBean.setProp1("prop1");
		
		NewClassModifyBean nestBean = new NewClassModifyBean();
		nestBean.setProp1("prop1prop1");
		nestBean.setProp2("prop2prop2");
		newBean.setProp2(nestBean);
		newBean.setProp3("prop3");
		
		// 新しいクラスでマーシャル
		byte[] bytes = MinimumMarshaller.marshal(newBean);
		

		// ログ
		System.out.println(newBean.getClass().getSimpleName() + " binary size is " + bytes.length);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		HexDump.dump(bytes, 0, os, 0);
		System.out.println(os.toString());
		System.out.println("");


		// マッピングの強制変更
		classIdRMap.remove(NewClassModifyBean.class);
		classIdRMap.put(OldClassModifyBean.class, classId);
		classMap.put(classId, OldClassModifyBean.class);
		

		// 古いクラスでアンマーシャル
		ParentBean oldBean = (ParentBean) MinimumMarshaller.unmarshal(bytes);

		// 内容チェック
		Assert.assertEquals("prop1", oldBean.getProp1());
		OldClassModifyBean nestOldBean = (OldClassModifyBean)oldBean.getProp2();
		Assert.assertEquals("prop1prop1", nestOldBean.getProp1());
		Assert.assertEquals("prop3", oldBean.getProp3());

		
		// 後片付け
		handlerMap.remove(parentClassId);
		classIdRMap.remove(ParentBean.class);
		classMap.remove(parentClassId);
		
		
		handlerMap.remove(classId);
		classIdRMap.remove(OldClassModifyBean.class);
		classMap.remove(classId);
		
	}
}
