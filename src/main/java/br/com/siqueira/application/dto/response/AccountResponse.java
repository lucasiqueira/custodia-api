package br.com.siqueira.application.dto.response;

public record AccountResponse(
        String id,
        String name,
        String email,
        boolean active,
        String createdAt,
        String updatedAt) {
}
