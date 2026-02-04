package br.com.siqueira.application.dto.request;

import br.com.siqueira.domain.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateAccountRequest(

        @NotBlank String name,

        @NotNull AccountType type,

        @NotNull Boolean active) {
}