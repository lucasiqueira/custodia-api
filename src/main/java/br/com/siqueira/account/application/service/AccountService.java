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
        return this.load(id);
    }

    @Transactional
    public Account createAccount(Account account) {
        if (accountRepository.existsByNameAndType(account.getName(), account.getType())) {
            throw new AccountAlreadyExistsException(account.getName(), account.getType());
        }

        return accountRepository.save(account);
    }

    @Transactional
    public Account updateAccount(Long id, String name, AccountType type) {
        Account account = this.load(id);
        account.update(name, type);
        return accountRepository.save(account);
    }

    @Transactional
    public Account deactivate(Long id) {
        Account account = this.load(id);
        account.deactivate();
        return accountRepository.save(account);
    }

    @Transactional
    public Account activate(Long id) {
        Account account = this.load(id);
        account.activate();
        return accountRepository.save(account);
    }

    private Account load(Long id) {
        return accountRepository.getAccountById(id);
    }

}