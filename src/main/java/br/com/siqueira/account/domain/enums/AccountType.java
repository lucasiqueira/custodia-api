package br.com.siqueira.account.domain.enums;

import lombok.Getter;

@Getter
public enum AccountType {
    CHECKING("CHECKING", "Conta Corrente"),
    SAVINGS("SAVINGS", "Conta Poupança"),
    CREDIT_CARD("CREDIT_CARD", "Cartão de Crédito");

    private final String type;
    private final String displayName;

    AccountType(String type, String displayName) {
        this.type = type;
        this.displayName = displayName;
    }
}
