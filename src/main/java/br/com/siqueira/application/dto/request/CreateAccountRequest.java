package br.com.siqueira.application.dto.request;

import br.com.siqueira.domain.enums.AccountType;

public class CreateAccountRequest {
    public String name;
    public AccountType type;
}
