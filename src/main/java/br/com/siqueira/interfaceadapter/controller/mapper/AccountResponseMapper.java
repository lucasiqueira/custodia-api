package br.com.siqueira.interfaceadapter.controller.mapper;

import br.com.siqueira.application.dto.response.AccountResponse;
import br.com.siqueira.domain.model.Account;

public class AccountResponseMapper {

    public static AccountResponse from(Account model) {
        return new AccountResponse(
                model.getId().toString(),
                model.getName(),
                model.getType().getDisplayName(),
                model.isActive(),
                model.getCreatedAt().toString(),
                model.getUpdatedAt().toString()
        );
    }
}
