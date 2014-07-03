package org.mk300.marshal.minimum.test.pojo;



public class POJO00F {
	
	private double attribute1_ = 0;
	
	public POJO00F() {
		super();
	}

	public double getAttribute1() {
		return attribute1_;
	}

	public void setAttribute1(double attribute1) {
		attribute1_ = attribute1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(attribute1_);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		POJO00F other = (POJO00F) obj;
		if (Double.doubleToLongBits(attribute1_) != Double
				.doubleToLongBits(other.attribute1_))
			return false;
		return true;
	}

}
