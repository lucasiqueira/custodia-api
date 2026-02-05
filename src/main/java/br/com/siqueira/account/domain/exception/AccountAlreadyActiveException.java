package br.com.siqueira.account.domain.exception;

public class AccountAlreadyActiveException extends RuntimeException {

    public AccountAlreadyActiveException() {
        super("Account is already active");
    }
}

