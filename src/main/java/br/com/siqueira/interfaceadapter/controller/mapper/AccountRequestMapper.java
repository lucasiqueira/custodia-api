package br.com.siqueira.interfaceadapter.controller.mapper;

import br.com.siqueira.application.dto.request.CreateAccountRequest;
import br.com.siqueira.domain.model.Account;

public final class AccountRequestMapper {

    private AccountRequestMapper() {}

    public static Account toNewAccount(CreateAccountRequest request) {
        return Account.createNew(request.name, request.type);
    }
}

