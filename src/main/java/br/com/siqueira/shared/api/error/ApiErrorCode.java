package br.com.siqueira.shared.api.error;

import jakarta.ws.rs.core.Response.Status;

public enum ApiErrorCode {
    ACCOUNT_NOT_FOUND(404, "ACCOUNT_NOT_FOUND", "Account not found"),
    ACCOUNT_ALREADY_EXISTS(409, "ACCOUNT_ALREADY_EXISTS", "Account already exists"),
    ACCOUNT_ALREADY_ACTIVE(409, "ACCOUNT_ALREADY_ACTIVE", "Account is already active"),
    ACCOUNT_ALREADY_INACTIVE(409, "ACCOUNT_ALREADY_INACTIVE", "Account is already inactive"),
    VALIDATION_ERROR(400, "VALIDATION_ERROR", "Validation error"),
    INTERNAL_ERROR(500, "INTERNAL_ERROR", "Internal server error"),
    CATEGORY_ALREADY_EXISTS(409, "CATEGORY_ALREADY_EXISTS", "Category already exists"),
    CATEGORY_NOT_FOUND(404, "CATEGORY_NOT_FOUND", "Category not found");

    private final Status httpStatus;
    private final String code;
    private final String message;

    ApiErrorCode(int httpStatus, String code, String message) {
        this.httpStatus = Status.fromStatusCode(httpStatus);
        this.code = code;
        this.message = message;
    }

    public Status status() {
        return httpStatus;
    }

    public String code() {
        return code;
    }

    public String defaultMessage() {
        return message;
    }
}
