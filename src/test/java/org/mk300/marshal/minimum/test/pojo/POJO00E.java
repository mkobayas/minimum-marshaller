package org.mk300.marshal.minimum.test.pojo;



public class POJO00E {

	private boolean attribute1_ = false;
	
	public POJO00E() {
		super();
	}

	public boolean getAttribute1() {
		return attribute1_;
	}

	public void setAttribute1(boolean attribute1) {
		attribute1_ = attribute1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (attribute1_ ? 1231 : 1237);
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
		POJO00E other = (POJO00E) obj;
		if (attribute1_ != other.attribute1_)
			return false;
		return true;
	}

}
