package org.mk300.marshal.minimum.test.pojo;



public class POJO00H {
	
	private long attribute1_ = 0;
	
	public POJO00H() {
		super();
	}

	public long getAttribute1() {
		return attribute1_;
	}

	public void setAttribute1(long attribute1) {
		attribute1_ = attribute1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (attribute1_ ^ (attribute1_ >>> 32));
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
		POJO00H other = (POJO00H) obj;
		if (attribute1_ != other.attribute1_)
			return false;
		return true;
	}

}
