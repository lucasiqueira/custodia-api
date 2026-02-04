package br.com.siqueira.interfaceadapter.controller.exception;

import br.com.siqueira.application.exception.AccountAlreadyExistsException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class AccountAlreadyExistsExceptionMapper
        implements ExceptionMapper<AccountAlreadyExistsException> {

    @Override
    public Response toResponse(AccountAlreadyExistsException ex) {
        return Response.status(Response.Status.CONFLICT)
                .entity(new ErrorResponse(
                        "ACCOUNT_ALREADY_EXISTS",
                        ex.getMessage()
                ))
                .build();
    }
}
