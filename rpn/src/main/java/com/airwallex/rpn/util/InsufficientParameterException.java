package com.airwallex.rpn.util;

public class InsufficientParameterException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InsufficientParameterException(String message) {
        super(message);
    }
}
