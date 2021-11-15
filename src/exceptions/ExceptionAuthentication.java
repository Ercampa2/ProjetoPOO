package exceptions;

public class ExceptionAuthentication extends Exception{
    public ExceptionAuthentication(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionAuthentication(String message) {
        super(message);
    }
}
