package br.com.siqueira.interfaceadapter.controller.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.json.bind.JsonbException;

@Provider
public class JsonbExceptionMapper
        implements ExceptionMapper<JsonbException> {

    @Override
    public Response toResponse(JsonbException exception) {

        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(new ErrorResponse(
                        "INVALID_REQUEST",
                        extractMessage(exception)
                ))
                .build();
    }

    private String extractMessage(Throwable ex) {
        Throwable cause = ex.getCause();
        if (cause != null) {
            return cause.getMessage();
        }
        return ex.getMessage();
    }
}
