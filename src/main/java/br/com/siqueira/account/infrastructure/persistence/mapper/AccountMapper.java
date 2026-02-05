package br.com.siqueira.account.infrastructure.persistence.mapper;

import br.com.siqueira.account.domain.enums.AccountType;
import br.com.siqueira.account.domain.model.Account;
import br.com.siqueira.account.infrastructure.persistence.entity.AccountEntity;

public final class AccountMapper {

    private AccountMapper() {
    }

    public static Account toModel(AccountEntity entity) {
        return Account.rehydrate(
                entity.getId(),
                entity.getName(),
                AccountType.valueOf(entity.getType()),
                entity.isActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }

    public static AccountEntity toEntity(Account model) {
        if (model == null)
            return null;

        AccountEntity entity = new AccountEntity();
        entity.setName(model.getName());
        entity.setType(model.getType().name());
        entity.setActive(model.isActive());

        return entity;
    }
}
