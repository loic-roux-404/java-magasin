package Exceptions;

public class InvalidOrderException extends Exception {
    public InvalidOrderException(String reason) {
        super(reason);
    }
}
