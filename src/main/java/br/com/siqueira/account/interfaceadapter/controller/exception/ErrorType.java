package br.com.siqueira.account.interfaceadapter.controller.exception;

import br.com.siqueira.account.application.exception.AccountAlreadyExistsException;
import br.com.siqueira.account.application.exception.AccountNotFoundException;
import br.com.siqueira.account.domain.exception.AccountAlreadyActiveException;
import br.com.siqueira.account.domain.exception.AccountAlreadyInactiveException;
import jakarta.json.bind.JsonbException;
import jakarta.validation.ConstraintViolationException;

public enum ErrorType {
    ACCOUNT_NOT_FOUND(404, "ACCOUNT_NOT_FOUND", AccountNotFoundException.class),
    ACCOUNT_ALREADY_EXISTS(409, "ACCOUNT_ALREADY_EXISTS", AccountAlreadyExistsException.class),
    ACCOUNT_ALREADY_ACTIVE(409, "ACCOUNT_ALREADY_ACTIVE", AccountAlreadyActiveException.class),
    ACCOUNT_ALREADY_INACTIVE(409, "ACCOUNT_ALREADY_INACTIVE", AccountAlreadyInactiveException.class),
    VALIDATION_ERROR(400, "VALIDATION_ERROR", ConstraintViolationException.class, JsonbException.class),
    INTERNAL_ERROR(500, "INTERNAL_ERROR");

    private final int status;
    private final String code;
    private final Class<? extends Throwable>[] exceptions;

    @SafeVarargs
    ErrorType(int status, String code, Class<? extends Throwable>... exceptions) {
        this.status = status;
        this.code = code;
        this.exceptions = exceptions;
    }

    public int status() {
        return status;
    }

    public String code() {
        return code;
    }

    public boolean matches(Throwable ex) {
        if (exceptions == null)
            return false;

        for (Class<? extends Throwable> clazz : exceptions) {
            if (clazz.isInstance(ex)) {
                return true;
            }
        }
        return false;
    }

    public static ErrorType fromException(Throwable ex) {
        for (ErrorType type : values()) {
            if (type.matches(ex)) {
                return type;
            }
        }
        return INTERNAL_ERROR;
    }
}
