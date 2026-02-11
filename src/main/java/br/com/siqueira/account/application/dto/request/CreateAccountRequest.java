package br.com.siqueira.account.application.dto.request;

import br.com.siqueira.account.domain.enums.AccountType;

public class CreateAccountRequest {
    public String name;
    public AccountType type;
}
