package org.mk300.marshal.minimum.test.pojo;


public class PrimitiveWrapperData {

	private Byte byteValue;
	private Character charValue;
	private Short shortValue;
	private Integer intValue;
	private Long longValue;
	private Float floatValue;
	private Double doubleValue;
	private Boolean booleanValue;

	public Byte getByteValue() {
		return byteValue;
	}

	public void setByteValue(Byte byteValue) {
		this.byteValue = byteValue;
	}

	public Short getShortValue() {
		return shortValue;
	}

	public void setShortValue(Short shortValue) {
		this.shortValue = shortValue;
	}

	public Integer getIntValue() {
		return intValue;
	}

	public void setIntValue(Integer intValue) {
		this.intValue = intValue;
	}

	public Long getLongValue() {
		return longValue;
	}

	public void setLongValue(Long longValue) {
		this.longValue = longValue;
	}

	public Float getFloatValue() {
		return floatValue;
	}

	public void setFloatValue(Float floatValue) {
		this.floatValue = floatValue;
	}

	public Double getDoubleValue() {
		return doubleValue;
	}

	public void setDoubleValue(Double doubleValue) {
		this.doubleValue = doubleValue;
	}

	public Boolean getBooleanValue() {
		return booleanValue;
	}

	public void setBooleanValue(Boolean booleanValue) {
		this.booleanValue = booleanValue;
	}


	public Character getCharValue() {
		return charValue;
	}

	public void setCharValue(Character charValue) {
		this.charValue = charValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((booleanValue == null) ? 0 : booleanValue.hashCode());
		result = prime * result
				+ ((byteValue == null) ? 0 : byteValue.hashCode());
		result = prime * result
				+ ((charValue == null) ? 0 : charValue.hashCode());
		result = prime * result
				+ ((doubleValue == null) ? 0 : doubleValue.hashCode());
		result = prime * result
				+ ((floatValue == null) ? 0 : floatValue.hashCode());
		result = prime * result
				+ ((intValue == null) ? 0 : intValue.hashCode());
		result = prime * result
				+ ((longValue == null) ? 0 : longValue.hashCode());
		result = prime * result
				+ ((shortValue == null) ? 0 : shortValue.hashCode());
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
		PrimitiveWrapperData other = (PrimitiveWrapperData) obj;
		if (booleanValue == null) {
			if (other.booleanValue != null)
				return false;
		} else if (!booleanValue.equals(other.booleanValue))
			return false;
		if (byteValue == null) {
			if (other.byteValue != null)
				return false;
		} else if (!byteValue.equals(other.byteValue))
			return false;
		if (charValue == null) {
			if (other.charValue != null)
				return false;
		} else if (!charValue.equals(other.charValue))
			return false;
		if (doubleValue == null) {
			if (other.doubleValue != null)
				return false;
		} else if (!doubleValue.equals(other.doubleValue))
			return false;
		if (floatValue == null) {
			if (other.floatValue != null)
				return false;
		} else if (!floatValue.equals(other.floatValue))
			return false;
		if (intValue == null) {
			if (other.intValue != null)
				return false;
		} else if (!intValue.equals(other.intValue))
			return false;
		if (longValue == null) {
			if (other.longValue != null)
				return false;
		} else if (!longValue.equals(other.longValue))
			return false;
		if (shortValue == null) {
			if (other.shortValue != null)
				return false;
		} else if (!shortValue.equals(other.shortValue))
			return false;
		return true;
	}

}
