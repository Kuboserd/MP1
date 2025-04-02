package exceptions;

public class IllegalInvokeTimeException extends RuntimeException {
    public IllegalInvokeTimeException(String message) {
        super(message);
    }
}