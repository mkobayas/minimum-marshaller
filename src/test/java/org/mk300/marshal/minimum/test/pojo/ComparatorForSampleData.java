package org.mk300.marshal.minimum.test.pojo;

import java.util.Comparator;

public class ComparatorForSampleData implements Comparator<SampleData> {

	@Override
	public int compare(SampleData o1, SampleData o2) {
		return o2.getP1().compareTo(o1.getP1());
	}

}
