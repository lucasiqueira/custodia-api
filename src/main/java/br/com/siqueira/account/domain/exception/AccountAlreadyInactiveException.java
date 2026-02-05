package br.com.siqueira.account.domain.exception;

public class AccountAlreadyInactiveException extends RuntimeException {

    public AccountAlreadyInactiveException() {
        super("Account is already inactive");
    }
}
