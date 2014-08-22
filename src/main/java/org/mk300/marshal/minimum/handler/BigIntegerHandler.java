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
import java.math.BigInteger;

import org.mk300.marshal.minimum.MarshalHandler;
import org.mk300.marshal.minimum.io.NaturalNumberIoHelper;
import org.mk300.marshal.minimum.io.OInput;
import org.mk300.marshal.minimum.io.OOutput;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
public class BigIntegerHandler implements MarshalHandler<BigInteger> {

	@Override
	public void writeObject(OOutput out, BigInteger number) throws IOException {
		byte[] bytes = number.toByteArray();
		NaturalNumberIoHelper.writeNaturalNumber(out, bytes.length);
		out.write(bytes);
	}

	@Override
	public BigInteger readObject(OInput in, Class<BigInteger> clazz) throws IOException {
		int size = NaturalNumberIoHelper.readNaturalNumber(in);
		byte[] bytes = new byte[size];
		in.readFully(bytes);
		BigInteger number = new BigInteger(bytes);
		return number;
	}
}
