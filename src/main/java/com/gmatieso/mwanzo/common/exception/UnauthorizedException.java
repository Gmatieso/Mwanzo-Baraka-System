package com.gmatieso.mwanzo.common.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException() {
        super("You are not allowed to perform this action");
    }
}
