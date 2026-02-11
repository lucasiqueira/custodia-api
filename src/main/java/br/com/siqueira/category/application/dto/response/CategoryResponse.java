package br.com.siqueira.category.application.dto.response;

public record CategoryResponse(
        Long id,
        String name,
        String type,
        String description,
        boolean active,
        String createdAt,
        String updatedAt) {
}
