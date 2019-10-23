package by.andersen.kudko.threads.model.exception;

public class TechnicalEcxeption extends ThreadsLogisticCompanyException {
    public TechnicalEcxeption(String message, Throwable cause) {
        super(message, cause);
    }

    public TechnicalEcxeption(Throwable cause) {
        super(cause);
    }

    public TechnicalEcxeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
