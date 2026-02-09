package br.com.siqueira.account.infrastructure.persistence.repository;

import java.util.List;

import br.com.siqueira.account.domain.enums.AccountType;
import br.com.siqueira.account.domain.model.Account;
import br.com.siqueira.account.infrastructure.persistence.entity.AccountEntity;
import br.com.siqueira.account.infrastructure.persistence.mapper.AccountMapper;
import br.com.siqueira.shared.api.error.BusinessException;
import br.com.siqueira.shared.api.error.ErrorType;
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

    public Account getAccountById(Long id) {
        AccountEntity entity = findById(id);
        if (entity == null) {
            throw new BusinessException(ErrorType.ACCOUNT_NOT_FOUND);
        }
        return AccountMapper.toModel(entity);
    }

    public Account save(Account account) {

        if (account.getId() == null) {
            AccountEntity entity = AccountMapper.toEntity(account);
            persist(entity);
            flush();
            return AccountMapper.toModel(entity);
        }

        AccountEntity entity = findById(account.getId());
        if (entity == null) {
            throw new BusinessException(ErrorType.ACCOUNT_NOT_FOUND);
        }

        entity.setName(account.getName());
        entity.setType(account.getType().name());
        entity.setActive(account.isActive());

        flush();
        return AccountMapper.toModel(entity);
    }

    public boolean existsByNameAndType(String name, AccountType type) {
        return find("name = ?1 and type = ?2", name, type.name()).count() > 0;
    }

}
