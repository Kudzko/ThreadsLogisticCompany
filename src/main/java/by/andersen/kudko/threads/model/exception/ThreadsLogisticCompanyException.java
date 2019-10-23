package by.andersen.kudko.threads.model.exception;

public class ThreadsLogisticCompanyException extends Exception {
    public ThreadsLogisticCompanyException() {
    }

    public ThreadsLogisticCompanyException(String message) {
        super(message);
    }

    public ThreadsLogisticCompanyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ThreadsLogisticCompanyException(Throwable cause) {
        super(cause);
    }

    public ThreadsLogisticCompanyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
