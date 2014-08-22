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
import java.sql.Date;

import org.mk300.marshal.minimum.MarshalHandler;
import org.mk300.marshal.minimum.io.OInput;
import org.mk300.marshal.minimum.io.OOutput;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
public class SqlDateHandler implements MarshalHandler<Date> {

	@Override
	public void writeObject(OOutput out, Date date) throws IOException {
		out.writeLong(date.getTime());
	}

	@Override
	public Date readObject(OInput in, Class<Date> clazz) throws IOException {
		long time = in.readLong();
		Date date = new Date(time);
		return date;
	}

}
