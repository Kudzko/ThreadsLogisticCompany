package by.andersen.kudko.threads.model.exception;

public class CustomRunTimeException extends RuntimeException {
    public CustomRunTimeException() {
    }

    public CustomRunTimeException(String message) {
        super(message);
    }

    public CustomRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomRunTimeException(Throwable cause) {
        super(cause);
    }

    public CustomRunTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
