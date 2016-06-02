package org.mk300.marshal.minimum.test.pojo;

public class MyEnumObject {

	private MyEnum myEnum;

	public MyEnum getMyEnum() {
		return myEnum;
	}

	public void setMyEnum(MyEnum myEnum) {
		this.myEnum = myEnum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((myEnum == null) ? 0 : myEnum.hashCode());
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
		MyEnumObject other = (MyEnumObject) obj;
		if (myEnum != other.myEnum)
			return false;
		return true;
	}
	
	
	
}
