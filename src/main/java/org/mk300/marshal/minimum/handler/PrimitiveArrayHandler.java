/*
 * Copyright 2014 Masazumi Kobayashi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mk300.marshal.minimum.handler;


import java.io.IOException;

import org.mk300.marshal.common.MarshalException;
import org.mk300.marshal.minimum.MarshalHandler;
import org.mk300.marshal.minimum.io.NaturalNumberIoHelper;
import org.mk300.marshal.minimum.io.OInputStream;
import org.mk300.marshal.minimum.io.OOutputStream2;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
@SuppressWarnings("rawtypes")
public class PrimitiveArrayHandler implements MarshalHandler {

	private static final Class<? extends byte[]> byteArrayClass = new byte[0].getClass();
	private static final Class<? extends short[]> shortArrayClass = new short[0].getClass();
	private static final Class<? extends int[]> intArrayClass = new int[0].getClass();
	private static final Class<? extends long[]> longArrayClass = new long[0].getClass();
	private static final Class<? extends boolean[]> booleanArrayClass = new boolean[0].getClass();
	private static final Class<? extends float[]> floatArrayClass = new float[0].getClass();
	private static final Class<? extends double[]> doubleArrayClass = new double[0].getClass();
	private static final Class<? extends char[]> charArrayClass = new char[0].getClass();
	
	@Override
	public void writeObject(OOutputStream2 out, Object o) throws IOException {
		
		if(o.getClass() == byteArrayClass) {
			byte[] array = (byte[])o;
			NaturalNumberIoHelper.writeNaturalNumber(out, array.length);
			out.write(array);
		} else if( o.getClass() == shortArrayClass ) {
			short[] array = (short[])o;
			NaturalNumberIoHelper.writeNaturalNumber(out, array.length);
			for(int i=0; i< array.length; i++ ) {
				out.writeShort(array[i]);
			}			
		} else if( o.getClass() == intArrayClass ) {
			int[] array = (int[])o;
			NaturalNumberIoHelper.writeNaturalNumber(out, array.length);
			for(int i=0; i< array.length; i++ ) {
				out.writeInt(array[i]);
			}
		} else if( o.getClass() == longArrayClass ) {
			long[] array = (long[])o;
			NaturalNumberIoHelper.writeNaturalNumber(out, array.length);
			for(int i=0; i< array.length; i++ ) {
				out.writeLong(array[i]);
			}
		} else if( o.getClass() == booleanArrayClass ) {
			boolean[] array = (boolean[])o;
			NaturalNumberIoHelper.writeNaturalNumber(out, array.length);
			for(int i=0; i< array.length; i++ ) {
				out.writeBoolean(array[i]);
			}
		} else if( o.getClass() == floatArrayClass ) {
			float[] array = (float[])o;
			NaturalNumberIoHelper.writeNaturalNumber(out, array.length);
			for(int i=0; i< array.length; i++ ) {
				out.writeFloat(array[i]);
			}
		} else if( o.getClass() == doubleArrayClass ) {
			double[] array = (double[])o;
			NaturalNumberIoHelper.writeNaturalNumber(out, array.length);
			for(int i=0; i< array.length; i++ ) {
				out.writeDouble(array[i]);
			}
		} else if( o.getClass() == charArrayClass ) {
			char[] array = (char[])o;
			NaturalNumberIoHelper.writeNaturalNumber(out, array.length);
			for(int i=0; i< array.length; i++ ) {
				out.writeChar(array[i]);
			}
		} else {
			throw new MarshalException("不明な配列型です." + o);
		}
		
		
	}

	@Override
	public Object readObject(OInputStream in, Class clazz)
			throws IOException {

		int size = NaturalNumberIoHelper.readNaturalNumber(in);
		
		if(clazz == byteArrayClass) {
			byte[] array = new byte[size];
			in.readFully(array);
			return array;
		} else if(clazz == shortArrayClass) {
			short[] array = new short[size];
			for(int i=0; i<size ; i++) {
				array[i] = in.readShort();
			}			
			return array;
		} else if(clazz == intArrayClass) {
			int[] array = new int[size];
			for(int i=0; i<size ; i++) {
				array[i] = in.readInt();
			}			
			return array;
		} else if(clazz == longArrayClass) {
			long[] array = new long[size];
			for(int i=0; i<size ; i++) {
				array[i] = in.readLong();
			}			
			return array;
		} else if(clazz == booleanArrayClass) {
			boolean[] array = new boolean[size];
			for(int i=0; i<size ; i++) {
				array[i] = in.readBoolean();
			}			
			return array;
		} else if(clazz == floatArrayClass) {
			float[] array = new float[size];
			for(int i=0; i<size ; i++) {
				array[i] = in.readFloat();
			}			
			return array;
		} else if(clazz == doubleArrayClass) {
			double[] array = new double[size];
			for(int i=0; i<size ; i++) {
				array[i] = in.readDouble();
			}			
			return array;
		} else if(clazz == charArrayClass) {
			char[] array = new char[size];
			for(int i=0; i<size ; i++) {
				array[i] = in.readChar();
			}			
			return array;
		} else {
			throw new MarshalException("不明な配列型です." + clazz);	
		}
		
	}

}
