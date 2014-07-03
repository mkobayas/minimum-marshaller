package org.mk300.marshal.minimum.test;


import java.util.Date;

import org.junit.Test;
import org.mk300.marshal.minimum.test.pojo.POJO001;
import org.mk300.marshal.minimum.test.pojo.VariableFieldObject;


public class VariableFieldObjectTest {

	@Test
	public void variableFieldObjectTest() throws Exception {
		
		VariableFieldObject obj = new VariableFieldObject();

		obj.setAttribute1_("hogehoge");
		obj.setAttribute2_(new Date());
		obj.setAttribute3_(100);
		obj.setAttribute4_("hello".getBytes());
		obj.setAttribute5_(true);
		obj.setAttribute6_(5.1);
		obj.setAttribute7_(6.2f);
		obj.setAttribute8_(999999);

		POJO001 pojo;
		
		pojo = new POJO001();
		pojo.setAttribute1("pojoList1");
		obj.getList_().add(pojo);
		
		pojo = new POJO001();
		pojo.setAttribute1("pojoList2");
		obj.getList_().add(pojo);

		pojo = new POJO001();
		pojo.setAttribute1("pojoList3");
		obj.getList_().add(pojo);
		
		
		pojo = new POJO001();
		pojo.setAttribute1("pojopojo");
		obj.setPojo0001(pojo);
		
		TestUtil.testAndPrintHexAndCheck(obj);
		
		
	}
	

}
