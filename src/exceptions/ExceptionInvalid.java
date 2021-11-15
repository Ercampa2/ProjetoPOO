package exceptions;

public class ExceptionInvalid extends RuntimeException{
    public ExceptionInvalid(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionInvalid(String message) {
        super(message);
    }
}
