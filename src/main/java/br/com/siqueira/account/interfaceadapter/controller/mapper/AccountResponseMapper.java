package br.com.siqueira.account.interfaceadapter.controller.mapper;

import java.time.format.DateTimeFormatter;
import java.util.List;

import br.com.siqueira.account.application.dto.response.AccountResponse;
import br.com.siqueira.account.domain.model.Account;

public class AccountResponseMapper {

    private static final DateTimeFormatter ISO = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static AccountResponse from(Account model) {
        return new AccountResponse(
                model.getId(),
                model.getName(),
                model.getType().getDisplayName(),
                model.isActive(),
                model.getCreatedAt().format(ISO),
                model.getUpdatedAt().format(ISO));
    }

    public static List<AccountResponse> from(List<Account> models) {
        return models.stream()
                .map(AccountResponseMapper::from)
                .toList();
    }
}
