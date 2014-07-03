package org.mk300.marshal.minimum.test.pojo;

import java.math.BigDecimal;

public class POJO00I {
	
	private BigDecimal attribute1_ = null;
	
	public POJO00I() {
		super();
	}

	public BigDecimal getAttribute1() {
		return attribute1_;
	}

	public void setAttribute1(BigDecimal attribute1) {
		attribute1_ = attribute1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attribute1_ == null) ? 0 : attribute1_.hashCode());
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
		POJO00I other = (POJO00I) obj;
		if (attribute1_ == null) {
			if (other.attribute1_ != null)
				return false;
		} else if (!attribute1_.equals(other.attribute1_))
			return false;
		return true;
	}

}
