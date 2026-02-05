package br.com.siqueira.account.interfaceadapter.controller.exception;

public enum ErrorType {

    ACCOUNT_NOT_FOUND(404, "ACCOUNT_NOT_FOUND"),
    ACCOUNT_ALREADY_EXISTS(409, "ACCOUNT_ALREADY_EXISTS"),
    ACCOUNT_ALREADY_ACTIVE(409, "ACCOUNT_ALREADY_ACTIVE"),
    ACCOUNT_ALREADY_INACTIVE(409, "ACCOUNT_ALREADY_INACTIVE"),
    VALIDATION_ERROR(400, "VALIDATION_ERROR"),
    INTERNAL_ERROR(500, "INTERNAL_ERROR");

    private final int status;
    private final String code;

    ErrorType(int status, String code) {
        this.status = status;
        this.code = code;
    }

    public int status() {
        return status;
    }

    public String code() {
        return code;
    }
}

