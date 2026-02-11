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

        String traceId = UUID.randomUUID().toString();
        ApiErrorCode errorCode = resolveErrorCode(exception);
        String message = resolveMessage(exception, errorCode, traceId);
        String origin = resolveOrigin(exception);

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                errorCode.status().getStatusCode(),
                errorCode.code(),
                message,
                origin,
                requestContext.getUriInfo().getPath(),
                traceId);

        logError(traceId, exception);

        return Response.status(errorCode.status())
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

    private ApiErrorCode resolveErrorCode(Throwable ex) {

        if (ex instanceof ApiException apiException) {
            return apiException.getErrorCode();
        }

        if (ex instanceof ConstraintViolationException) {
            return ApiErrorCode.VALIDATION_ERROR;
        }

        if (ex instanceof JsonbException) {
            return ApiErrorCode.VALIDATION_ERROR;
        }

        if (ex instanceof IllegalArgumentException) {
            return ApiErrorCode.VALIDATION_ERROR;
        }

        return ApiErrorCode.INTERNAL_ERROR;
    }

    private String resolveMessage(Throwable ex, ApiErrorCode code, String traceId) {

        if (ex instanceof ApiException) {
            return ex.getMessage();
        }

        if (code == ApiErrorCode.VALIDATION_ERROR) {
            return ex.getMessage();
        }

        if (code == ApiErrorCode.INTERNAL_ERROR) {
            return "Unexpected error. TraceId=" + traceId;
        }

        return ex.getMessage();
    }
}
