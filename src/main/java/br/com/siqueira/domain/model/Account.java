package br.com.siqueira.domain.model;

import java.time.LocalDateTime;

import br.com.siqueira.domain.enums.AccountType;
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
}
