package org.mk300.marshal.minimum.test.pojo;

import java.util.Arrays;

import org.mk300.marshal.minimum.test.TestUtil;

public class PrimitiveArrayData {
	private byte[] byteArray;
	private char[] charArray;
	private short[] shortArray;
	private int[] intArray;
	private long[] longArray;
	private float[] floatArray;
	private double[] doubleArray;
	private boolean[] booleanArray;
	
	
	private byte[][] byteArray2;

	public byte[][] getByteArray2() {
		return byteArray2;
	}

	public void setByteArray2(byte[][] byteArray2) {
		this.byteArray2 = byteArray2;
	}

	public boolean[] getBooleanArray() {
		return booleanArray;
	}

	public byte[] getByteArray() {
		return byteArray;
	}

	public void setByteArray(byte[] byteArray) {
		this.byteArray = byteArray;
	}

	public char[] getCharArray() {
		return charArray;
	}

	public void setCharArray(char[] charArray) {
		this.charArray = charArray;
	}

	public short[] getShortArray() {
		return shortArray;
	}

	public void setShortArray(short[] shortArray) {
		this.shortArray = shortArray;
	}

	public int[] getIntArray() {
		return intArray;
	}

	public void setIntArray(int[] intArray) {
		this.intArray = intArray;
	}

	public long[] getLongArray() {
		return longArray;
	}

	public void setLongArray(long[] longArray) {
		this.longArray = longArray;
	}

	public float[] getFloatArray() {
		return floatArray;
	}

	public void setFloatArray(float[] floatArray) {
		this.floatArray = floatArray;
	}

	public double[] getDoubleArray() {
		return doubleArray;
	}

	public void setDoubleArray(double[] doubleArray) {
		this.doubleArray = doubleArray;
	}

	public boolean[] isBooleanArray() {
		return booleanArray;
	}

	public void setBooleanArray(boolean[] booleanArray) {
		this.booleanArray = booleanArray;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(booleanArray);
		result = prime * result + Arrays.hashCode(byteArray);
		result = prime * result + Arrays.hashCode(byteArray2);
		result = prime * result + Arrays.hashCode(charArray);
		result = prime * result + Arrays.hashCode(doubleArray);
		result = prime * result + Arrays.hashCode(floatArray);
		result = prime * result + Arrays.hashCode(intArray);
		result = prime * result + Arrays.hashCode(longArray);
		result = prime * result + Arrays.hashCode(shortArray);
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
		PrimitiveArrayData other = (PrimitiveArrayData) obj;
		if (!TestUtil.deepEquals(booleanArray, other.booleanArray))
			return false;
		if (!TestUtil.deepEquals(byteArray, other.byteArray))
			return false;
		if (!TestUtil.deepEquals(byteArray2, other.byteArray2))
			return false;
		if (!TestUtil.deepEquals(charArray, other.charArray))
			return false;
		if (!TestUtil.deepEquals(doubleArray, other.doubleArray))
			return false;
		if (!TestUtil.deepEquals(floatArray, other.floatArray))
			return false;
		if (!TestUtil.deepEquals(intArray, other.intArray))
			return false;
		if (!TestUtil.deepEquals(longArray, other.longArray))
			return false;
		if (!TestUtil.deepEquals(shortArray, other.shortArray))
			return false;
		return true;
	}


	
	
}
