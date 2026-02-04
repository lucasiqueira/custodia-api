package br.com.siqueira.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.siqueira.domain.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Account {
    private UUID id;
    private String name;
    private AccountType type;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
