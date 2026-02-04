package br.com.siqueira.interfaceadapter.controller.account;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;

import br.com.siqueira.application.dto.request.CreateAccountRequest;
import br.com.siqueira.application.dto.response.AccountResponse;
import br.com.siqueira.application.service.AccountService;
import br.com.siqueira.domain.model.Account;
import br.com.siqueira.interfaceadapter.controller.mapper.AccountResponseMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
@Tag(name = "Account", description = "Account management endpoints")
@Path("v1/accounts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GET
    @Path("")
    public List<AccountResponse> getAllAccounts() {
        return accountService.getAllAccounts().stream()
                .map(AccountResponseMapper::from)
                .toList();
    }

    @POST
    @Path("")
    @APIResponse(
        responseCode = "201",
        description = "Account created successfully",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(implementation = AccountResponse.class)
        )
    )
    public RestResponse<AccountResponse> createAccount(CreateAccountRequest request) {
        Account accountCreated = accountService.createAccount(request.name, request.type);
        return RestResponse.status(
                RestResponse.Status.CREATED,
                AccountResponseMapper.from(accountCreated));
    }
}
