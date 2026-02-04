package br.com.siqueira.interfaceadapter.controller;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.siqueira.application.dto.response.AccountResponse;
import br.com.siqueira.application.service.AccountService;
import br.com.siqueira.interfaceadapter.controller.mapper.AccountResponseMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@ApplicationScoped
@Tag(name = "Account", description = "Account management endpoints")
@Path("v1/accounts")
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
}
