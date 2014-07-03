package org.mk300.marshal.minimum.test.pojo;



public class POJO00G {
	
	private float attribute1_ = 0;
	
	public POJO00G() {
		super();
	}

	public float getAttribute1() {
		return attribute1_;
	}

	public void setAttribute1(float attribute1) {
		attribute1_ = attribute1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(attribute1_);
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
		POJO00G other = (POJO00G) obj;
		if (Float.floatToIntBits(attribute1_) != Float
				.floatToIntBits(other.attribute1_))
			return false;
		return true;
	}

}
