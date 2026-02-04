package br.com.siqueira.infrastructure.persistence.mapper;

import br.com.siqueira.domain.enums.AccountType;
import br.com.siqueira.domain.model.Account;
import br.com.siqueira.infrastructure.persistence.entity.AccountEntity;

public final class AccountMapper {

    private AccountMapper() {}

    public static Account toModel(AccountEntity entity) {
        if (entity == null) return null;

        return new Account(
            entity.getId(),
            entity.getName(),
            AccountType.fromString(entity.getType()),
            entity.isActive(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }

    public static AccountEntity toEntity(Account model) {
        if (model == null) return null;

        AccountEntity entity = new AccountEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setType(model.getType().name());
        entity.setActive(model.isActive());
        entity.setCreatedAt(model.getCreatedAt());
        entity.setUpdatedAt(model.getUpdatedAt());

        return entity;
    }
}

