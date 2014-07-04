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
import java.sql.Timestamp;

import org.mk300.marshal.minimum.MarshalHandler;
import org.mk300.marshal.minimum.io.OInputStream;
import org.mk300.marshal.minimum.io.OOutputStream;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
public class SqlTimestampHandler implements MarshalHandler<Timestamp> {

	@Override
	public void writeObject(OOutputStream out, Timestamp timestamp) throws IOException {
		out.writeLong(timestamp.getTime());
		out.writeInt(timestamp.getNanos());
	}

	@Override
	public Timestamp readObject(OInputStream in, Class<Timestamp> clazz) throws IOException {
		long time = in.readLong();
		int nano = in.readInt();
		Timestamp t = new Timestamp(time);
		t.setNanos(nano);
		return t;
	}

}
