package org.mk300.marshal.minimum.test.pojo;


public class POJO005 {
	private String attribute1_ = null;
	private String attribute2_ = null;
	private String attribute3_ = null;
	private String attribute4_ = null;
	private String attribute5_ = null;
	
	public POJO005() {
		super();
	}

	public String getAttribute1() {
		return attribute1_;
	}

	public void setAttribute1(String attribute1) {
		attribute1_ = attribute1;
	}

	public String getAttribute2() {
		return attribute2_;
	}

	public void setAttribute2(String attribute2) {
		attribute2_ = attribute2;
	}

	public String getAttribute3() {
		return attribute3_;
	}

	public void setAttribute3(String attribute3) {
		attribute3_ = attribute3;
	}

	public String getAttribute4() {
		return attribute4_;
	}

	public void setAttribute4(String attribute4) {
		attribute4_ = attribute4;
	}

	public String getAttribute5() {
		return attribute5_;
	}

	public void setAttribute5(String attribute5) {
		attribute5_ = attribute5;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attribute1_ == null) ? 0 : attribute1_.hashCode());
		result = prime * result
				+ ((attribute2_ == null) ? 0 : attribute2_.hashCode());
		result = prime * result
				+ ((attribute3_ == null) ? 0 : attribute3_.hashCode());
		result = prime * result
				+ ((attribute4_ == null) ? 0 : attribute4_.hashCode());
		result = prime * result
				+ ((attribute5_ == null) ? 0 : attribute5_.hashCode());
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
		POJO005 other = (POJO005) obj;
		if (attribute1_ == null) {
			if (other.attribute1_ != null)
				return false;
		} else if (!attribute1_.equals(other.attribute1_))
			return false;
		if (attribute2_ == null) {
			if (other.attribute2_ != null)
				return false;
		} else if (!attribute2_.equals(other.attribute2_))
			return false;
		if (attribute3_ == null) {
			if (other.attribute3_ != null)
				return false;
		} else if (!attribute3_.equals(other.attribute3_))
			return false;
		if (attribute4_ == null) {
			if (other.attribute4_ != null)
				return false;
		} else if (!attribute4_.equals(other.attribute4_))
			return false;
		if (attribute5_ == null) {
			if (other.attribute5_ != null)
				return false;
		} else if (!attribute5_.equals(other.attribute5_))
			return false;
		return true;
	}

	
}
