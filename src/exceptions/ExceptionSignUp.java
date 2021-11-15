package exceptions;

public class ExceptionSignUp extends ExceptionAuthentication{
    public ExceptionSignUp(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionSignUp(String message) {
        super(message);
    }
}
