package br.com.siqueira.infrastructure.persistence.repository;

import java.util.List;

import br.com.siqueira.domain.model.Account;
import br.com.siqueira.infrastructure.persistence.entity.AccountEntity;
import br.com.siqueira.infrastructure.persistence.mapper.AccountMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccountRepository
        implements PanacheRepositoryBase<AccountEntity, Long> {

    public List<Account> getAccounts() {
        return findAll()
                .stream()
                .map(AccountMapper::toModel)
                .toList();
    }

    public List<Account> findByName(String name) {
        return find("name", name)
                .stream()
                .map(AccountMapper::toModel)
                .toList();
    }

    public Account createAccount(Account account) {
        AccountEntity entity = AccountMapper.toEntity(account);

        persist(entity);
        flush();

        return AccountMapper.toModel(entity);
    }

    public Account getAccountById(Long id) {
        AccountEntity entity = find("id", id).firstResult();
        if (entity == null) {
            return null;
        }
        return AccountMapper.toModel(entity);
    }

    public Account updateAccount(Account account) {
        AccountEntity entity = findById(account.getId());
        if (entity == null) {
            return null;
        }

        entity.setName(account.getName());
        entity.setType(account.getType().toString());
        entity.setActive(account.isActive());

        persist(entity);
        flush();

        return AccountMapper.toModel(entity);
    }
}
