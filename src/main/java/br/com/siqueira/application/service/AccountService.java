package br.com.siqueira.application.service;

import java.util.List;

import br.com.siqueira.domain.model.Account;
import br.com.siqueira.infrastructure.persistence.repository.AccountRepository;
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

    @Transactional
    public Account createAccount(Account account) {
        List<Account> existingAccounts = accountRepository.findByName(account.getName());
        if (!existingAccounts.isEmpty()) {
            throw new IllegalArgumentException("Account with name '" + account.getName() + "' already exists");
        }
        return accountRepository.createAccount(account);
    }
}
