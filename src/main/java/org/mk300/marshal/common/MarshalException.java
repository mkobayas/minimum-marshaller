package org.mk300.marshal.common;

import java.io.IOException;

public class MarshalException extends IOException {

	private static final long serialVersionUID = 1L;

	public MarshalException() {
	}

	public MarshalException(String message) {
		super(message);
	}

	public MarshalException(Throwable cause) {
		super(cause);
	}

	public MarshalException(String message, Throwable cause) {
		super(message, cause);
	}

}
