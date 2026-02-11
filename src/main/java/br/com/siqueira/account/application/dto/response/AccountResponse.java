package br.com.siqueira.account.application.dto.response;

public record AccountResponse(
        Long id,
        String name,
        String type,
        boolean active,
        String createdAt,
        String updatedAt) {
}
