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

package org.mk300.marshal.common;

/**
 * 
 * @author mkobayas@redhat.com
 *
 */
public class InfiniteLoopException extends MarshalException {

	private static final long serialVersionUID = 1L;

	public InfiniteLoopException() {
	}

	public InfiniteLoopException(String message) {
		super(message);
	}

	public InfiniteLoopException(Throwable cause) {
		super(cause);
	}

	public InfiniteLoopException(String message, Throwable cause) {
		super(message, cause);
	}

}
