package br.com.siqueira.account.application.service;

import java.util.List;

import br.com.siqueira.account.application.exception.AccountAlreadyExistsException;
import br.com.siqueira.account.domain.enums.AccountType;
import br.com.siqueira.account.domain.model.Account;
import br.com.siqueira.account.infrastructure.persistence.repository.AccountRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.getAccounts();
    }

    public Account getAccountById(Long id) {
        return accountRepository.getAccountById(id);
    }

    @Transactional
    public Account createAccount(Account account) {
        List<Account> existingAccounts = accountRepository.findByName(account.getName());
        if (!existingAccounts.isEmpty() && existingAccounts.stream().anyMatch(a -> a.getType().equals(account.getType()))) {
            throw new AccountAlreadyExistsException(account.getName(), account.getType());
        }

        return accountRepository.createAccount(account);
    }

    @Transactional
    public Account updateAccount(Long id, String name, AccountType type, boolean active) {
        Account account = accountRepository.getAccountById(id);
        account.setName(name);
        account.setType(type);
        account.setActive(active);
        return accountRepository.updateAccount(account);
    }

    @Transactional
    public Account deactivate(Long id) {
        Account account = accountRepository.getAccountById(id);
        account.deactivate();
        accountRepository.save(account);
        return account;
    }

    @Transactional
    public Account activate(Long id) {
        Account account = accountRepository.getAccountById(id);
        account.activate();
        accountRepository.save(account);
        return account;
    }
}