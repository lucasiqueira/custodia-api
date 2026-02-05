package br.com.siqueira.account.interfaceadapter.controller.exception;

import br.com.siqueira.account.application.exception.*;
import br.com.siqueira.account.domain.exception.*;
import jakarta.json.bind.JsonbException;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.ExceptionMapper;

@Provider
public class GlobalExceptionMapper
        implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {

        ErrorType errorType = resolveErrorType(exception);

        return Response.status(errorType.status())
                .entity(new ErrorResponse(
                        errorType.code(),
                        exception.getMessage()))
                .build();
    }

    private ErrorType resolveErrorType(Throwable ex) {

        if (ex instanceof AccountNotFoundException) {
            return ErrorType.ACCOUNT_NOT_FOUND;
        }

        if (ex instanceof AccountAlreadyExistsException) {
            return ErrorType.ACCOUNT_ALREADY_EXISTS;
        }

        if (ex instanceof AccountAlreadyActiveException) {
            return ErrorType.ACCOUNT_ALREADY_ACTIVE;
        }

        if (ex instanceof AccountAlreadyInactiveException) {
            return ErrorType.ACCOUNT_ALREADY_INACTIVE;
        }

        if (ex instanceof ConstraintViolationException) {
            return ErrorType.VALIDATION_ERROR;
        }

        if (ex instanceof JsonbException) {
            return ErrorType.VALIDATION_ERROR;
        }

        return ErrorType.INTERNAL_ERROR;
    }
}
