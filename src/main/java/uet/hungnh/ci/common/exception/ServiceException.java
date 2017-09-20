package uet.hungnh.ci.common.exception;

public class ServiceException extends Exception {

    private String message;
    private int httpStatusCode;

    public ServiceException(String message, int httpStatusCode) {
        super(message);
        this.message = super.getMessage();
        this.httpStatusCode = httpStatusCode;
    }

    public ServiceException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getMessage());
        this.message = super.getMessage();
        this.httpStatusCode = exceptionMessage.getHttpStatus().value();
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
