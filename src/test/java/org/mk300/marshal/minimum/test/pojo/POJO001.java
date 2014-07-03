package org.mk300.marshal.minimum.test.pojo;



public class POJO001 {

	private String attribute1_ = null;
	
	public POJO001() {
		super();
	}
		
	public String getAttribute1() {
		return attribute1_;
	}

	public void setAttribute1(String attribute1) {
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
		POJO001 other = (POJO001) obj;
		if (attribute1_ == null) {
			if (other.attribute1_ != null)
				return false;
		} else if (!attribute1_.equals(other.attribute1_))
			return false;
		return true;
	}
	
	
}
