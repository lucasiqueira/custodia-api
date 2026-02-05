package br.com.siqueira.account.application.dto.response;

public record AccountResponse(
        Long id,
        String name,
        String email,
        boolean active,
        String createdAt,
        String updatedAt) {
}
