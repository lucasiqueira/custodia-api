package br.com.siqueira.category.infrastructure.persistence.mapper;

import br.com.siqueira.category.domain.enums.CategoryType;
import br.com.siqueira.category.domain.model.Category;
import br.com.siqueira.category.infrastructure.persistence.entity.CategoryEntity;

public final class CategoryMapper {
    private CategoryMapper() {
    }

    public static Category toModel(CategoryEntity entity) {
        if (entity == null) {
            return null;
        }

        validateEntity(entity);

        return Category.rehydrate(
                entity.getId(),
                entity.getName(),
                mapType(entity.getType(), entity.getId()),
                entity.getDescription(),
                entity.isActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }

    public static CategoryEntity toEntity(Category model) {
        if (model == null) {
            return null;
        }

        if (model.getName() == null || model.getName().isBlank()) {
            throw new IllegalStateException("Category name cannot be null or blank");
        }

        if (model.getType() == null) {
            throw new IllegalStateException("Category type cannot be null");
        }

        CategoryEntity entity = new CategoryEntity();
        entity.setName(model.getName());
        entity.setType(model.getType().name());
        entity.setDescription(model.getDescription());
        entity.setActive(model.isActive());

        return entity;
    }

    private static void validateEntity(CategoryEntity entity) {
        if (entity.getId() == null) {
            throw new IllegalStateException("CategoryEntity with null id");
        }

        if (entity.getName() == null) {
            throw new IllegalStateException("CategoryEntity name is null (id=" + entity.getId() + ")");
        }

        if (entity.getType() == null) {
            throw new IllegalStateException("CategoryEntity type is null (id=" + entity.getId() + ")");
        }
    }

    private static CategoryType mapType(String typeStr, Long entityId) {
        try {
            return CategoryType.valueOf(typeStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Invalid category type '" + typeStr + "' for CategoryEntity with id " + entityId);
        }
    }
}
