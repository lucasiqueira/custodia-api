package br.com.siqueira.category.domain.model;

import java.time.LocalDateTime;

import br.com.siqueira.category.domain.enums.CategoryType;
import br.com.siqueira.shared.utils.Parameters;
import lombok.Getter;

@Getter
public class Category {
    private Long id;
    private String name;
    private CategoryType type;
    private String description;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Category(
            Long id,
            String name,
            CategoryType type,
            String description,
            boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Category createNew (String name, CategoryType type, String description) {
        Parameters.requireNonBlank(name, "name");

        return new Category (
            null,
            name,
            type,
            description,
            true,
            null,
            null);
    }

    public static Category rehydrate(
            Long id,
            String name,
            CategoryType type,
            String description,
            boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        Parameters.requireNonNull(id, "id");
        Parameters.requireNonBlank(name, "name");

        return new Category (
                id,
                name,
                type,
                description,
                active,
                createdAt,
                updatedAt);
    }

    public void update(String name, CategoryType type, String description) {
        Parameters.requireNonBlank(name, "name");
        this.name = name;
        this.type = type;
        this.description = description;
    }
}
