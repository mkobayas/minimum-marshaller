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

package org.mk300.marshal.minimum.io;

import java.io.IOException;

/**
 * Data*Streamに対して、可変バイト数で自然数の書込み、読込みを実施する。<br>
 * 扱う自然数と対応するバイト数は以下のとおり。<br>
 * <li>0 - 127 -> 1byte</li>
 * <li>128 - 16383 -> 2byte</li>
 * <li>16384 - 2097151 -> 3byte</li>
 * <li>2097152 - 268435455 -> 4byte</li>
 * <li>268435456 - Integer.MAX_VALUE -> 5byte</li>
 * @author mkobayas@redhat.com
 *
 */
public final class NaturalNumberIoHelper {

	private final static int dv1 = 128;     // 7bit 2^7
	private final static int dv2 = 16384;     // 14bit 2^14
	private final static int dv3 = 2097152;     // 21bit 2^21
	private final static int dv4 = 268435456;     // 28bit 2^28


	/**
	 * DataOutputStreamに対して、可変バイト数で自然数の書込みを実施する。<br>
	 * 扱う自然数と対応するバイト数は以下のとおり。<br>
	 * <li>0 - 127 -> 1byte</li>
	 * <li>128 - 16383 -> 2byte</li>
	 * <li>16384 - 2097151 -> 3byte</li>
	 * <li>2097152 - 268435455 -> 4byte</li>
	 * <li>268435456 - Integer.MAX_VALUE -> 5byte</li>
	 * @param dos
	 * @param naturalNumber
	 * @throws IOException
	 */
	public static final void writeNaturalNumber(OOutput dos, int naturalNumber) throws IOException {
		if(naturalNumber < 0) {
			throw new IOException(naturalNumber + " is not natural number");
		}
		
		if( naturalNumber < dv1 ) { // 7bit
			int b1  = naturalNumber;
			dos.writeByte(b1);
			
		} else if ( naturalNumber < dv2) { // 14bit
			int b1 = (naturalNumber / dv1) * -1;
			int b2 = (naturalNumber % dv1);
			dos.writeByte(b1);
			dos.writeByte(b2);

		} else if( naturalNumber < dv3) { // 21bit
			int b1 = (naturalNumber / dv2) * -1;
			int b2 = ((b1*dv2 + naturalNumber) / dv1 + 1) * -1 ;
			int b3 = (naturalNumber % dv1);
			dos.writeByte(b1);
			dos.writeByte(b2);
			dos.writeByte(b3);

		} else if( naturalNumber < dv4) { // 28bit
			int b1 = (naturalNumber / dv3) * -1;
			int b2 = ((b1*dv3 + naturalNumber) / dv2 + 1) * -1;
			int b3 = ((b1*dv3 + (b2 + 1)*dv2 + naturalNumber) / dv1 + 1) * -1 ;
			int b4 = (naturalNumber % dv1);
			dos.writeByte(b1);
			dos.writeByte(b2);
			dos.writeByte(b3);
			dos.writeByte(b4);

		} else { 
			int b1 = (naturalNumber / dv4) * -1;
			int b2 = ( (b1*dv4 + naturalNumber) / dv3 + 1) * -1;
			int b3 = ( (b1*dv4 + (b2+1)*dv3 + naturalNumber) / dv2 + 1) * -1;
			int b4 = ( (b1*dv4 + (b2+1)*dv3 + (b3+1)*dv2 + naturalNumber) / dv1 + 1) * -1 ;
			int b5 = (naturalNumber % dv1);
			dos.writeByte(b1);
			dos.writeByte(b2);
			dos.writeByte(b3);
			dos.writeByte(b4);
			dos.writeByte(b5);
		}
	}
	
	/**
	 * DataInputStreamに対して、可変バイト数の自然数の読込みを実施する。
	 * @param dis
	 * @return
	 * @throws IOException
	 */
	public static final int readNaturalNumber(OInput dis) throws IOException {

		int naturalNumber = 0;
		byte b1 = dis.readByte();
		
		if( b1 >= 0) {
			naturalNumber = b1;
		} else {
			byte b2 = dis.readByte();
			
			if(b2 >= 0) {
				naturalNumber = b1 * dv1 * -1 ;
				naturalNumber += b2;
			} else {
				
				byte b3 = dis.readByte();
				
				if(b3 >= 0) {
					naturalNumber += b1  * dv2 * -1;
					naturalNumber += (b2+1) * dv1 * -1;
					naturalNumber += b3;
				} else {
					byte b4 = dis.readByte();
					
					if(b4 >=0) {
						naturalNumber += b1  * dv3 * -1;
						naturalNumber += (b2+1) * dv2 * -1;
						naturalNumber += (b3+1) * dv1 * -1;
						naturalNumber += b4;
						
					} else {
						byte b5 = dis.readByte();

						naturalNumber += b1  * dv4 * -1;
						naturalNumber += (b2+1) * dv3 * -1;
						naturalNumber += (b3+1) * dv2 * -1;
						naturalNumber += (b4+1) * dv1 * -1;
						naturalNumber += b5;

					}
				}
			}
		}
		
		return naturalNumber;
	}
}
