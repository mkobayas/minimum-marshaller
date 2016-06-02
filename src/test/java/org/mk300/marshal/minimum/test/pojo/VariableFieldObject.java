package org.mk300.marshal.minimum.test.pojo;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.mk300.marshal.common.ExternOrder;


public class VariableFieldObject {

	@ExternOrder(1)
	private String attribute1_;
	@ExternOrder(2)
	private Date attribute2_;
	@ExternOrder(3)
	private int attribute3_;
	@ExternOrder(4)
	private byte[] attribute4_;
	@ExternOrder(5)
	private List<POJO001> list_ = new ArrayList<POJO001>();
	@ExternOrder(6)
	private boolean attribute5_;
	@ExternOrder(7)
	private POJO001 pojo0001;
	@ExternOrder(8)
	private double attribute6_;
	@ExternOrder(9)
	private float attribute7_;
	@ExternOrder(10)
	private long attribute8_;
	
	public String getAttribute1_() {
		return attribute1_;
	}
	public void setAttribute1_(String attribute1_) {
		this.attribute1_ = attribute1_;
	}
	public Date getAttribute2_() {
		return attribute2_;
	}
	public void setAttribute2_(Date attribute2_) {
		this.attribute2_ = attribute2_;
	}
	public int getAttribute3_() {
		return attribute3_;
	}
	public void setAttribute3_(int attribute3_) {
		this.attribute3_ = attribute3_;
	}
	public byte[] getAttribute4_() {
		return attribute4_;
	}
	public void setAttribute4_(byte[] attribute4_) {
		this.attribute4_ = attribute4_;
	}
	public List<POJO001> getList_() {
		return list_;
	}
	public void setList_(List<POJO001> list_) {
		this.list_ = list_;
	}
	public boolean isAttribute5_() {
		return attribute5_;
	}
	public void setAttribute5_(boolean attribute5_) {
		this.attribute5_ = attribute5_;
	}
	public POJO001 getPojo0001() {
		return pojo0001;
	}
	public void setPojo0001(POJO001 pojo0001) {
		this.pojo0001 = pojo0001;
	}
	public double getAttribute6_() {
		return attribute6_;
	}
	public void setAttribute6_(double attribute6_) {
		this.attribute6_ = attribute6_;
	}
	public float getAttribute7_() {
		return attribute7_;
	}
	public void setAttribute7_(float attribute7_) {
		this.attribute7_ = attribute7_;
	}
	public long getAttribute8_() {
		return attribute8_;
	}
	public void setAttribute8_(long attribute8_) {
		this.attribute8_ = attribute8_;
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
		result = prime * result
				+ ((pojo0001 == null) ? 0 : pojo0001.hashCode());
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
		VariableFieldObject other = (VariableFieldObject) obj;
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
		if (pojo0001 == null) {
			if (other.pojo0001 != null)
				return false;
		} else if (!pojo0001.equals(other.pojo0001))
			return false;
		return true;
	}
	
	
}
