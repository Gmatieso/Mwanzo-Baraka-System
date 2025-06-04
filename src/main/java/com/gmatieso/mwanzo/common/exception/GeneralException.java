package com.gmatieso.mwanzo.common.exception;

public class GeneralException extends RuntimeException {
    public GeneralException(String message) {
        super(message);
    }

    public GeneralException(){super("An error occurred while processing your request. Please try again later.");}
}
