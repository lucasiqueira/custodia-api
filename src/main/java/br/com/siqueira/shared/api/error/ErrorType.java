package br.com.siqueira.shared.api.error;

public enum ErrorType {
    ACCOUNT_NOT_FOUND(404, "ACCOUNT_NOT_FOUND", "Account not found"),
    ACCOUNT_ALREADY_EXISTS(409, "ACCOUNT_ALREADY_EXISTS", "Account already exists"),
    ACCOUNT_ALREADY_ACTIVE(409, "ACCOUNT_ALREADY_ACTIVE", "Account is already active"),
    ACCOUNT_ALREADY_INACTIVE(409, "ACCOUNT_ALREADY_INACTIVE", "Account is already inactive"),
    VALIDATION_ERROR(400, "VALIDATION_ERROR", "Validation error"),
    INTERNAL_ERROR(500, "INTERNAL_ERROR", "Internal server error");

    private final int status;
    private final String code;
    private final String defaultMessage;

    ErrorType(int status, String code, String defaultMessage) {
        this.status = status;
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public int status() {
        return status;
    }

    public String code() {
        return code;
    }

    public String defaultMessage() {
        return defaultMessage;
    }
}
