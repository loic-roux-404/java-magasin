package com.app.Exceptions;

public class EntityManagerProxyException extends InternalException {
    public EntityManagerProxyException(Exception e) {
        super("Error during entity manager operation / transaction");
        e.printStackTrace();
    }
}
