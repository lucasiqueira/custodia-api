package br.com.siqueira.account.application.exception;

import br.com.siqueira.account.domain.enums.AccountType;

public class AccountAlreadyExistsException extends RuntimeException {

    public AccountAlreadyExistsException(String name, AccountType type) {
        super("Account with name '" + name + "' and type '" + type.getType() + "' already exists");
    }
}