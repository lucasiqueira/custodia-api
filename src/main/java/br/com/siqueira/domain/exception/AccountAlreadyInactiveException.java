package br.com.siqueira.domain.exception;

public class AccountAlreadyInactiveException extends RuntimeException {

    public AccountAlreadyInactiveException() {
        super("Account is already inactive");
    }
}
