package main.java.proj.Exceptions;

public class InvalidPageException extends InternalException {
    public InvalidPageException(String name) {
        super("Invalid page :" + name);
    }
}