package org.mk300.marshal.minimum.test.pojo;


import java.util.Date;

import org.mk300.marshal.common.ExternOrder;


public class SampleData {

	@ExternOrder(1)
	private String p1 = "Hello";

	@ExternOrder(2)
	private String p2 = "World";
	
	@ExternOrder(3)
	private Date p3 = new Date();
	
	@ExternOrder(4)
	private SampleData p4 = null;
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((p1 == null) ? 0 : p1.hashCode());
		result = prime * result + ((p2 == null) ? 0 : p2.hashCode());
		result = prime * result + ((p3 == null) ? 0 : p3.hashCode());
		result = prime * result + ((p4 == null) ? 0 : p4.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SampleData other = (SampleData) obj;
		if (p1 == null) {
			if (other.p1 != null)
				return false;
		} else if (!p1.equals(other.p1))
			return false;
		if (p2 == null) {
			if (other.p2 != null)
				return false;
		} else if (!p2.equals(other.p2))
			return false;
		if (p3 == null) {
			if (other.p3 != null)
				return false;
		} else if (!p3.equals(other.p3))
			return false;
		if (p4 == null) {
			if (other.p4 != null)
				return false;
		} else if (!p4.equals(other.p4))
			return false;
		return true;
	}

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

	public SampleData getP4() {
		return p4;
	}

	public void setP4(SampleData p4) {
		this.p4 = p4;
	}

}
