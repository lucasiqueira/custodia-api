package br.com.siqueira.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

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
    private UUID id;
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
        LocalDateTime now = LocalDateTime.now();

        return new Account(
                UUID.randomUUID(),
                name,
                type,
                true,
                now,
                now);
    }
}
