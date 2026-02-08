package br.com.siqueira.account.interfaceadapter.controller.exception;

import br.com.siqueira.shared.api.error.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper
        implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {

        ErrorType errorType = ErrorType.fromException(exception);

        return Response.status(errorType.status())
                .entity(new ErrorResponse(
                        errorType.code(),
                        resolveMessage(exception)))
                .build();
    }

    private String resolveMessage(Throwable ex) {
        if (ex.getMessage() != null && !ex.getMessage().isBlank()) {
            return ex.getMessage();
        }
        return "Unexpected error";
    }
}
