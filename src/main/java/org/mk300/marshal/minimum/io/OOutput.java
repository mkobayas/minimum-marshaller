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
public interface OOutput {
	public void write(int b) throws IOException;
	public void write(byte[] b) throws IOException;
	public void write(byte[] b, int off, int len) throws IOException;
	public void writeBoolean(boolean v) throws IOException;
	public void writeByte(int v) throws IOException;
	public void writeShort(short v) throws IOException;
	public void writeChar(char v) throws IOException;
	public void writeInt(int v) throws IOException;
	public void writeLong(long v) throws IOException;
	public void writeFloat(float f) throws IOException;
	public void writeDouble(double d) throws IOException;
	public void writeObject(Object o) throws IOException;
	public void writeString(String str) throws IOException;
	public byte[] toBytes() throws IOException;
	public void flush() throws IOException;
	public void close() throws IOException;
    public int size() throws IOException;
	public int skipIntSize() throws IOException;
	public void writeIntDirect(int intValue, int index) throws IOException;
	public void writeUTF(String str) throws IOException;
}
