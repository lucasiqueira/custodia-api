package br.com.siqueira.application.service;

import java.util.List;

import br.com.siqueira.application.exception.AccountAlreadyExistsException;
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
}
