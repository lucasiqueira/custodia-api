package br.com.siqueira.account.application.dto.request;

import br.com.siqueira.account.domain.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateAccountRequest(
        @NotBlank String name,
        @NotNull AccountType type) {
}