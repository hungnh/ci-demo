package uet.hungnh.ci.common.exception;

import org.springframework.http.HttpStatus;

public enum ExceptionMessage {
    USER_NOT_FOUND("User not found.", HttpStatus.NOT_FOUND);

    private String message;
    private HttpStatus httpStatus;

    ExceptionMessage(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
