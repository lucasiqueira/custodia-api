package br.com.siqueira.domain.exception;

public class AccountAlreadyActiveException extends RuntimeException {

    public AccountAlreadyActiveException() {
        super("Account is already active");
    }
}

