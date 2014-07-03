package test.beans;

import org.mk300.marshal.common.ExternOrder;


public class NewClassModifyBean implements NestModifyBean {
	
	String prop1;

	// 追加された項目
	@ExternOrder(1)
	String prop2;

	public String getProp1() {
		return prop1;
	}

	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}

	public String getProp2() {
		return prop2;
	}

	public void setProp2(String prop2) {
		this.prop2 = prop2;
	}
	
}
