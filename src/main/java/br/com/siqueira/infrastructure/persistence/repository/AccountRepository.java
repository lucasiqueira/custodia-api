package br.com.siqueira.infrastructure.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import br.com.siqueira.domain.enums.AccountType;
import br.com.siqueira.domain.model.Account;
import br.com.siqueira.infrastructure.persistence.entity.AccountEntity;
import br.com.siqueira.infrastructure.persistence.mapper.AccountMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccountRepository
        implements PanacheRepositoryBase<AccountEntity, UUID> {

    public List<Account> getAccounts() {
        return findAll()
                .stream()
                .map(AccountMapper::toModel)
                .toList();
    }

    public Account createAccount(String name, AccountType type) {
        AccountEntity entity = new AccountEntity();
        entity.setId(UUID.randomUUID());
        entity.setName(name);
        entity.setType(type.name());
        entity.setActive(true);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());

        persist(entity);
        flush();

        return AccountMapper.toModel(entity);
    }
}
