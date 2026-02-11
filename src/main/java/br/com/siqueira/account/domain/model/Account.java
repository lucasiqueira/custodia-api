package br.com.siqueira.account.domain.model;

import java.time.LocalDateTime;

import br.com.siqueira.account.domain.enums.AccountType;
import br.com.siqueira.shared.api.error.ApiErrorCode;
import br.com.siqueira.shared.api.error.ApiException;
import br.com.siqueira.shared.utils.Parameters;
import lombok.Getter;

@Getter
public class Account {
    private Long id;
    private String name;
    private AccountType type;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Account(
            Long id,
            String name,
            AccountType type,
            boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Account createNew(String name, AccountType type) {
        Parameters.requireNonBlank(name, "name");

        return new Account(
                null,
                name,
                type,
                true,
                null,
                null);
    }

    public static Account rehydrate(
            Long id,
            String name,
            AccountType type,
            boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new Account(
                id, name, type, active, createdAt, updatedAt);
    }

    public void update(String name, AccountType type) {
        Parameters.requireNonBlank(name, "name");
        this.name = name;
        this.type = type;
    }

    public void deactivate() {
        if (!this.active) {
            throw new ApiException(ApiErrorCode.ACCOUNT_ALREADY_INACTIVE);
        }
        this.active = false;
    }

    public void activate() {
        if (this.active) {
            throw new ApiException(ApiErrorCode.ACCOUNT_ALREADY_ACTIVE);
        }
        this.active = true;
    }
}
