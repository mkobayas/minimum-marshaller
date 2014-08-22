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
 * 
 * @author mkobayas@redhat.com
 *
 */
public interface OInput {
	public Object readObject() throws IOException;
	public String readString() throws IOException;
	public void readFully(byte[] dest) throws IOException;
	public byte readByte() throws IOException;
	public boolean readBoolean() throws IOException;
	public int readInt() throws IOException;
	public long readLong() throws IOException;
	public short readShort() throws IOException;
	public char readChar() throws IOException;
	public float readFloat() throws IOException;
	public double readDouble() throws IOException;
	public void skip(int n) throws IOException;
	public OInput branch() throws IOException;
    public String readUTF() throws IOException;
}
