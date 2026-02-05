package br.com.siqueira.account.domain.model;

import java.time.LocalDateTime;

import br.com.siqueira.account.domain.enums.AccountType;
import br.com.siqueira.account.domain.exception.AccountAlreadyActiveException;
import br.com.siqueira.account.domain.exception.AccountAlreadyInactiveException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
public class Account {
    private Long id;
    private String name;
    private AccountType type;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Account(String name, AccountType type) {
        this.name = name;
        this.type = type;
    }

    public static Account createNew(String name, AccountType type) {

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
            LocalDateTime updatedAt
    ) {
        return new Account(
                id, name, type, active, createdAt, updatedAt
        );
    }

        public void deactivate() {
        if (!this.active) {
            throw new AccountAlreadyInactiveException();
        }
        this.active = false;
    }

    public void activate() {
        if (this.active) {
            throw new AccountAlreadyActiveException();
        }
        this.active = true;
    }
}
