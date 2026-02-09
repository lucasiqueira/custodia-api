package br.com.siqueira.shared.api.error;

public class ApiException extends RuntimeException {

    private final ApiErrorCode errorType;

    public ApiException(ApiErrorCode errorType) {
        super(errorType.defaultMessage());
        this.errorType = errorType;
    }

    public ApiException(ApiErrorCode errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public ApiErrorCode getErrorType() {
        return errorType;
    }
}

