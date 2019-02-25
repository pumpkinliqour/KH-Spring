package com.kh.spring.common.exception;

public class BoardException extends ExceptionInInitializerError{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6627527849489357291L;

	public BoardException() {
		super();
	}
	public BoardException(String msg) {
		super(msg);
	}
}
