package br.com.siqueira.application.service;

import java.util.List;

import br.com.siqueira.domain.model.Account;
import br.com.siqueira.infrastructure.persistance.repository.AccountRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccountService {
    private final AccountRepository accountRepository;

    AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.getAccounts();
    }
}
