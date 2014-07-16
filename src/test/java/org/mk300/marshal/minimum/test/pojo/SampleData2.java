package org.mk300.marshal.minimum.test.pojo;


import java.util.Date;

import org.mk300.marshal.common.ExternOrder;


public class SampleData2 {

	@ExternOrder(1)
	private String p1 = "Hello";

	@ExternOrder(2)
	private String p2 = "World";
	
	@ExternOrder(3)
	private Date p3 = new Date();
	
	@ExternOrder(4)
	private SampleData2 p4 = null;
	

	public String getP1() {
		return p1;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}

	public String getP2() {
		return p2;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}

	public Date getP3() {
		return p3;
	}

	public void setP3(Date p3) {
		this.p3 = p3;
	}

	public SampleData2 getP4() {
		return p4;
	}

	public void setP4(SampleData2 p4) {
		this.p4 = p4;
	}

}
