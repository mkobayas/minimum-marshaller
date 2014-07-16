package org.mk300.marshal.common;


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
