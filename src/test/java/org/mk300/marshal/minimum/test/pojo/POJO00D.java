package org.mk300.marshal.minimum.test.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class POJO00D {
	
	private List<Date> list_ = new ArrayList<Date>();

	public POJO00D() {
		super();
	}

	public void addDate(Date date) {
		list_.add(date);
	}
	
	public List<Date> getList() {
		return list_;
	}

	public void setList(List<Date> list) {
		list_ = list;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		POJO00D other = (POJO00D) obj;
		if (list_ == null) {
			if (other.list_ != null)
				return false;
		} else if (!list_.equals(other.list_))
			return false;
		return true;
	}
	
	
}
