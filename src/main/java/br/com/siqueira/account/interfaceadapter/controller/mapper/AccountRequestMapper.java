package br.com.siqueira.account.interfaceadapter.controller.mapper;

import br.com.siqueira.account.application.dto.request.CreateAccountRequest;
import br.com.siqueira.account.domain.model.Account;

public final class AccountRequestMapper {

    private AccountRequestMapper() {}

    public static Account toNewAccount(CreateAccountRequest request) {
        return Account.createNew(request.name, request.type);
    }
}

