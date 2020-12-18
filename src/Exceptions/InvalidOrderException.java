package Exceptions;

public class InvalidOrderException extends InternalException {
    public InvalidOrderException(String reason) {
        super(reason);
    }
}
