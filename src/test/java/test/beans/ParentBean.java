package test.beans;

import org.mk300.marshal.common.ExternOrder;


public class ParentBean {

	@ExternOrder(1)
	String prop1;

	@ExternOrder(2)
	NestModifyBean prop2;

	// 変更されるクラスのフィールドの後に出現するフィールド
	@ExternOrder(3)
	String prop3;


	public String getProp1() {
		return prop1;
	}


	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}


	public NestModifyBean getProp2() {
		return prop2;
	}


	public void setProp2(NestModifyBean prop2) {
		this.prop2 = prop2;
	}


	public String getProp3() {
		return prop3;
	}


	public void setProp3(String prop3) {
		this.prop3 = prop3;
	}

	
}
