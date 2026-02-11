package br.com.siqueira.account.infrastructure.persistence.mapper;

import br.com.siqueira.account.domain.enums.AccountType;
import br.com.siqueira.account.domain.model.Account;
import br.com.siqueira.account.infrastructure.persistence.entity.AccountEntity;

public final class AccountMapper {

    private AccountMapper() {
    }

    public static Account toModel(AccountEntity entity) {
        if (entity == null) {
            return null;
        }

        validateEntity(entity);

        return Account.rehydrate(
                entity.getId(),
                entity.getName(),
                mapType(entity.getType(), entity.getId()),
                entity.isActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }

    public static AccountEntity toEntity(Account model) {
        if (model == null) {
            return null;
        }

        if (model.getName() == null || model.getName().isBlank()) {
            throw new IllegalStateException("Account name cannot be null or blank");
        }

        if (model.getType() == null) {
            throw new IllegalStateException("Account type cannot be null");
        }

        AccountEntity entity = new AccountEntity();
        entity.setName(model.getName());
        entity.setType(model.getType().name());
        entity.setActive(model.isActive());

        return entity;
    }

    private static void validateEntity(AccountEntity entity) {
        if (entity.getId() == null) {
            throw new IllegalStateException("AccountEntity with null id");
        }

        if (entity.getName() == null) {
            throw new IllegalStateException("AccountEntity name is null (id=" + entity.getId() + ")");
        }

        if (entity.getType() == null) {
            throw new IllegalStateException("AccountEntity type is null (id=" + entity.getId() + ")");
        }
    }

    private static AccountType mapType(String typeParam, Long id) {
        try {
            return AccountType.valueOf(typeParam);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(
                    "Invalid AccountType in database: " + typeParam + " (id=" + id + ")", e);
        }
    }
}
