package org.mk300.marshal.minimum.test.pojo;

public class ComparableObject implements Comparable<ComparableObject> {

	private int order;
	
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public int compareTo(ComparableObject o) {
		return this.order - o.order;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + order;
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
		ComparableObject other = (ComparableObject) obj;
		if (order != other.order)
			return false;
		return true;
	}
	
	
}
