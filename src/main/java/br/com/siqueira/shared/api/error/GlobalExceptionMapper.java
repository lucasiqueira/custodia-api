package br.com.siqueira.shared.api.error;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.json.bind.JsonbException;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

    @Context
    ContainerRequestContext requestContext;

    @Override
    public Response toResponse(Throwable exception) {

        ErrorType errorType;
        String message;

        if (exception instanceof BusinessException be) {
            errorType = be.getErrorType();
            message = be.getMessage();
        }
        else if (exception instanceof ConstraintViolationException
                || exception instanceof JsonbException
                || exception instanceof IllegalArgumentException) {

            errorType = ErrorType.VALIDATION_ERROR;
            message = exception.getMessage();
        }
        else {
            errorType = ErrorType.INTERNAL_ERROR;
            message = "Unexpected error";
        }

        String traceId = UUID.randomUUID().toString();

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                errorType.status(),
                errorType.code(),
                message,
                requestContext.getUriInfo().getPath(),
                traceId
        );

        return Response.status(errorType.status())
                .entity(response)
                .build();
    }
}
