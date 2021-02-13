package com.app.Exceptions;

public class NotLoadedException extends RegisteryException {
    public NotLoadedException(String name) {
        super("Not loaded service : ", name);
    }
}
