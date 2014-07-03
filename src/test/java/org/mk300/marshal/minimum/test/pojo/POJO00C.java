package org.mk300.marshal.minimum.test.pojo;

import java.util.Arrays;

public class POJO00C {
	
	private byte[] attribute1_ = null;
	
	public POJO00C() {
		super();
	}

	public byte[] getAttribute1() {
		return attribute1_;
	}

	public void setAttribute1(byte[] attribute1) {
		attribute1_ = attribute1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(attribute1_);
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
		POJO00C other = (POJO00C) obj;
		if (!Arrays.equals(attribute1_, other.attribute1_))
			return false;
		return true;
	}

}
