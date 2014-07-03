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

import org.mk300.marshal.minimum.MarshalHandler;
import org.mk300.marshal.minimum.io.OInputStream;
import org.mk300.marshal.minimum.io.OOutputStream;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
public class PrimitiveWrapperHandler implements MarshalHandler {

	@Override
	public void writeObject(OOutputStream out, Object o) throws IOException {
		Class<?> c = o.getClass();
		if(c == Integer.class){
			out.writeInt((Integer)o);
			
		} else if(c == Long.class) {
			out.writeLong((Long)o);
			
		} else if(c == Short.class) {
			out.writeShort((Short)o);
			
		} else if(c == Boolean.class) {
			out.writeBoolean((Boolean)o);
			
		} else if(c == Double.class) {
			out.writeDouble((Double)o);
			
		} else if(c == Float.class) {
			out.writeFloat((Float)o);
			
		} else if(c == Character.class) {
			out.writeChar((Character)o);
			
		} else if( c == Byte.class) {
			out.writeByte((Byte)o);
		}
	}

	@Override
	public Object readObject(OInputStream in, Class<?> c)
			throws IOException {
		
		if(c == Integer.class){
			return in.readInt();
		} else if(c == Long.class) {
			return in.readLong();
		} else if(c == Short.class) {
			return  in.readShort();
		} else if(c == Boolean.class) {
			return in.readBoolean();
		} else if(c == Double.class) {
			return in.readDouble();
		} else if(c == Float.class) {
			return in.readFloat();
		} else if(c == Character.class) {
			return in.readChar();
		} else if(c == Byte.class) {
			return in.readByte();
		}
		
		throw new RuntimeException("このコードは到達しない.");
	}

}
