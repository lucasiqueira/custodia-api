package br.com.siqueira.infrastructure.persistance.repository;

import java.util.List;
import java.util.UUID;

import br.com.siqueira.domain.model.Account;
import br.com.siqueira.infrastructure.persistance.entity.AccountEntity;
import br.com.siqueira.infrastructure.persistance.mapper.AccountMapper;
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
}
