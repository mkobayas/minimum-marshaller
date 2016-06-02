package org.mk300.marshal.minimum.test.pojo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionData {

	List<PrimitiveWrapperData> list;
	Map<String, PrimitiveWrapperData> map;
	Set<PrimitiveWrapperData> set;
	
	List<List<PrimitiveWrapperData>> listlist;

	public List<PrimitiveWrapperData> getList() {
		return list;
	}

	public void setList(List<PrimitiveWrapperData> list) {
		this.list = list;
	}

	public Map<String, PrimitiveWrapperData> getMap() {
		return map;
	}

	public void setMap(Map<String, PrimitiveWrapperData> map) {
		this.map = map;
	}

	public Set<PrimitiveWrapperData> getSet() {
		return set;
	}

	public void setSet(Set<PrimitiveWrapperData> set) {
		this.set = set;
	}

	public List<List<PrimitiveWrapperData>> getListlist() {
		return listlist;
	}

	public void setListlist(List<List<PrimitiveWrapperData>> listlist) {
		this.listlist = listlist;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		result = prime * result
				+ ((listlist == null) ? 0 : listlist.hashCode());
		result = prime * result + ((map == null) ? 0 : map.hashCode());
		result = prime * result + ((set == null) ? 0 : set.hashCode());
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
		CollectionData other = (CollectionData) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		if (listlist == null) {
			if (other.listlist != null)
				return false;
		} else if (!listlist.equals(other.listlist))
			return false;
		if (map == null) {
			if (other.map != null)
				return false;
		} else if (!map.equals(other.map))
			return false;
		if (set == null) {
			if (other.set != null)
				return false;
		} else if (!set.equals(other.set))
			return false;
		return true;
	}
	
	
	
}
