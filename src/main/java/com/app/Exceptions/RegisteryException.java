package com.app.Exceptions;

public class RegisteryException extends InternalException {
    public RegisteryException(String name, String message) {
        super((message != null ? message : "Invalid service registration : ") + name);
    }
}
