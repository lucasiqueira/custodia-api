package br.com.siqueira.shared.api.error;

import java.time.LocalDateTime;
import java.util.UUID;

import io.quarkus.logging.Log;
import jakarta.json.bind.JsonbException;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

    private static final String BASE_PACKAGE = "br.com.siqueira";

    @Context
    ContainerRequestContext requestContext;

    @Override
    public Response toResponse(Throwable exception) {

        ApiErrorCode errorType;
        String message;

        if (exception instanceof ApiException be) {
            errorType = be.getErrorType();
            message = be.getMessage();
        } else if (exception instanceof ConstraintViolationException
                || exception instanceof JsonbException
                || exception instanceof IllegalArgumentException) {

            errorType = ApiErrorCode.VALIDATION_ERROR;
            message = exception.getMessage();
        } else {
            errorType = ApiErrorCode.INTERNAL_ERROR;
            message = "Unexpected error";
        }

        String origin = resolveOrigin(exception);
        String traceId = UUID.randomUUID().toString();

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                errorType.status(),
                errorType.code(),
                message,
                requestContext.getUriInfo().getPath(),
                traceId,
                origin);

        logError(traceId, exception);

        return Response.status(errorType.status())
                .entity(response)
                .build();
    }

    private String resolveOrigin(Throwable ex) {

        for (StackTraceElement element : ex.getStackTrace()) {

            String className = element.getClassName();

            if (!className.startsWith(BASE_PACKAGE) || className.contains(".shared.utils")) {
                continue;
            }

            return className.substring(BASE_PACKAGE.length() + 1) + "." + element.getMethodName() + ":"
                    + element.getLineNumber();
        }

        return "unknown";
    }

    private void logError(String traceId, Throwable exception) {
        Log.error("Trace ID: " + traceId);
        exception.printStackTrace();
    }

}
