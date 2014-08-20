package org.mk300.marshal.minimum.test.pojo;

public class UndefinedPojo {

	private SampleData p1;
	private SampleData p2;
	private UndefinedEnum en;
	
	public UndefinedEnum getEn() {
		return en;
	}
	public void setEn(UndefinedEnum en) {
		this.en = en;
	}
	public SampleData getP1() {
		return p1;
	}
	public void setP1(SampleData p1) {
		this.p1 = p1;
	}
	public SampleData getP2() {
		return p2;
	}
	public void setP2(SampleData p2) {
		this.p2 = p2;
	}
	
	
	
	
	
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((en == null) ? 0 : en.hashCode());
		result = prime * result + ((p1 == null) ? 0 : p1.hashCode());
		result = prime * result + ((p2 == null) ? 0 : p2.hashCode());
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
		UndefinedPojo other = (UndefinedPojo) obj;
		if (en != other.en)
			return false;
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
		return true;
	}

 
}
