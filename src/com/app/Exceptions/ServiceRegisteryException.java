package com.app.Exceptions;

public class ServiceRegisteryException extends InternalException {
    public ServiceRegisteryException(String name, String message) {
        super((message != null ? message : "Invalid service registration : ") + name);
    }
}
