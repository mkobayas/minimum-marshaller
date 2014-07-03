package org.mk300.marshal.minimum.test.pojo;



public class POJO00B {
	
	private int attribute1_ = 0;
	
	public POJO00B() {
		super();
	}

	public int getAttribute1() {
		return attribute1_;
	}

	public void setAttribute1(int attribute1) {
		attribute1_ = attribute1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + attribute1_;
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
		POJO00B other = (POJO00B) obj;
		if (attribute1_ != other.attribute1_)
			return false;
		return true;
	}

}
