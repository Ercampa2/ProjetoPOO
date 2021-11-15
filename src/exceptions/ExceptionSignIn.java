package exceptions;

public class ExceptionSignIn extends ExceptionAuthentication{
    public ExceptionSignIn(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionSignIn(String message) {
        super(message);
    }
}
