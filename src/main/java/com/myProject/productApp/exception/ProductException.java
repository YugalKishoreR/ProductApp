package com.sella.productApp.exception;

public class ProductException extends RuntimeException {

	private final String message;

	public ProductException(String message) {
		this.message = message;

	}

	public String getMessage() {
		return message;
	}

}
