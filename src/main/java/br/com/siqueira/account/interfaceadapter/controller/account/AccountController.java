package br.com.siqueira.account.interfaceadapter.controller.account;

import java.net.URI;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;

import br.com.siqueira.account.application.dto.request.CreateAccountRequest;
import br.com.siqueira.account.application.dto.request.UpdateAccountRequest;
import br.com.siqueira.account.application.dto.response.AccountResponse;
import br.com.siqueira.account.application.service.AccountService;
import br.com.siqueira.account.domain.model.Account;
import br.com.siqueira.account.interfaceadapter.controller.mapper.AccountRequestMapper;
import br.com.siqueira.account.interfaceadapter.controller.mapper.AccountResponseMapper;
import br.com.siqueira.shared.api.error.ErrorResponse;
import br.com.siqueira.shared.api.openapi.responses.BadRequestResponse;
import br.com.siqueira.shared.api.openapi.responses.ConflictResponse;
import br.com.siqueira.shared.api.openapi.responses.NotFoundResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
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
    @APIResponse(responseCode = "200", description = "List of accounts", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.ARRAY, implementation = AccountResponse.class)))
    public RestResponse<List<AccountResponse>> getAllAccounts() {
        return RestResponse.ok(AccountResponseMapper.from(accountService.getAllAccounts()));
    }

    @GET
    @Path("{id}")
    @APIResponse(responseCode = "200", description = "Account found", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = AccountResponse.class)))
    @NotFoundResponse
    public RestResponse<AccountResponse> getAccountById(@PathParam("id") Long id) {
        Account account = accountService.getAccountById(id);
        return RestResponse.ok(AccountResponseMapper.from(account));
    }

    @POST
    @APIResponse(responseCode = "201", description = "Account created", content = @Content(schema = @Schema(implementation = AccountResponse.class)))
    @BadRequestResponse
    @ConflictResponse
    public RestResponse<AccountResponse> createAccount(@Valid CreateAccountRequest request) {
        Account account = AccountRequestMapper.toNewAccount(request);
        Account created = accountService.createAccount(account);

        AccountResponse response = AccountResponseMapper.from(created);

        return RestResponse.ResponseBuilder
                .create(RestResponse.Status.CREATED, response)
                .location(URI.create("/v1/accounts/" + response.id()))
                .build();

    }

    @PUT
    @Path("{id}")
    @APIResponse(responseCode = "200", description = "Account updated", content = @Content(schema = @Schema(implementation = AccountResponse.class)))
    @BadRequestResponse
    @NotFoundResponse
    public RestResponse<AccountResponse> updateAccount(
            @PathParam("id") Long id,
            @Valid UpdateAccountRequest request) {
        Account updated = accountService.updateAccount(
                id,
                request.name(),
                request.type());

        return RestResponse.ok(AccountResponseMapper.from(updated));
    }

    @PATCH
    @Path("{id}/deactivate")
    @APIResponse(responseCode = "200", description = "Account deactivated successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = AccountResponse.class)))
    @NotFoundResponse
    @APIResponse(responseCode = "409", description = "Account cannot be deactivated", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public RestResponse<AccountResponse> deactivate(@PathParam("id") Long id) {
        Account account = accountService.deactivate(id);
        return RestResponse.ok(AccountResponseMapper.from(account));
    }

    @PATCH
    @Path("{id}/activate")
    @APIResponse(responseCode = "200", description = "Account activated successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = AccountResponse.class)))
    @NotFoundResponse
    @APIResponse(responseCode = "409", description = "Account cannot be activated", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public RestResponse<AccountResponse> activate(@PathParam("id") Long id) {
        Account account = accountService.activate(id);
        return RestResponse.ok(AccountResponseMapper.from(account));
    }
}
