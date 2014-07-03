package org.mk300.marshal.minimum.test.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class POJO00K {
	
	private String attribute1_ = null;
	private Date attribute2_ = null;
	private int attribute3_ = 0;
	private byte[] attribute4_ = null;
	private List<POJO001> list_ = new ArrayList<POJO001>();
	private boolean attribute5_ = false;
	private double attribute6_ = 0;
	private float attribute7_ = 0;
	private long attribute8_ = 0;
	
	public POJO00K() {
		super();
	}
	
	public void addDate(POJO001 date) {
		list_.add(date);
	}

	public String getAttribute1() {
		return attribute1_;
	}

	public void setAttribute1(String attribute1) {
		attribute1_ = attribute1;
	}

	public Date getAttribute2() {
		return attribute2_;
	}

	public void setAttribute2(Date attribute2) {
		attribute2_ = attribute2;
	}

	public int getAttribute3() {
		return attribute3_;
	}

	public void setAttribute3(int attribute3) {
		attribute3_ = attribute3;
	}

	public byte[] getAttribute4() {
		return attribute4_;
	}

	public void setAttribute4(byte[] attribute4) {
		attribute4_ = attribute4;
	}

	public List<POJO001> getList() {
		return list_;
	}

	public void setList(List<POJO001> list) {
		list_ = list;
	}

	public boolean isAttribute5() {
		return attribute5_;
	}

	public void setAttribute5(boolean attribute5) {
		attribute5_ = attribute5;
	}

	public double getAttribute6() {
		return attribute6_;
	}

	public void setAttribute6(double attribute6) {
		attribute6_ = attribute6;
	}

	public float getAttribute7() {
		return attribute7_;
	}

	public void setAttribute7(float attribute7) {
		attribute7_ = attribute7;
	}

	public long getAttribute8() {
		return attribute8_;
	}

	public void setAttribute8(long attribute8) {
		attribute8_ = attribute8;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attribute1_ == null) ? 0 : attribute1_.hashCode());
		result = prime * result
				+ ((attribute2_ == null) ? 0 : attribute2_.hashCode());
		result = prime * result + attribute3_;
		result = prime * result + Arrays.hashCode(attribute4_);
		result = prime * result + (attribute5_ ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(attribute6_);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Float.floatToIntBits(attribute7_);
		result = prime * result + (int) (attribute8_ ^ (attribute8_ >>> 32));
		result = prime * result + ((list_ == null) ? 0 : list_.hashCode());
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
		POJO00K other = (POJO00K) obj;
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
		if (attribute3_ != other.attribute3_)
			return false;
		if (!Arrays.equals(attribute4_, other.attribute4_))
			return false;
		if (attribute5_ != other.attribute5_)
			return false;
		if (Double.doubleToLongBits(attribute6_) != Double
				.doubleToLongBits(other.attribute6_))
			return false;
		if (Float.floatToIntBits(attribute7_) != Float
				.floatToIntBits(other.attribute7_))
			return false;
		if (attribute8_ != other.attribute8_)
			return false;
		if (list_ == null) {
			if (other.list_ != null)
				return false;
		} else if (!list_.equals(other.list_))
			return false;
		return true;
	}
}
