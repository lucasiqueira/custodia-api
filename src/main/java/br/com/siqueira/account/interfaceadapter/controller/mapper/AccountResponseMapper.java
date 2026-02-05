package br.com.siqueira.account.interfaceadapter.controller.mapper;

import br.com.siqueira.account.application.dto.response.AccountResponse;
import br.com.siqueira.account.domain.model.Account;

public class AccountResponseMapper {

    public static AccountResponse from(Account model) {
        return new AccountResponse(
                model.getId(),
                model.getName(),
                model.getType().getDisplayName(),
                model.isActive(),
                model.getCreatedAt().toString(),
                model.getUpdatedAt().toString()
        );
    }
}
