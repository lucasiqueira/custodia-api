package br.com.siqueira.category.infrastructure.persistence.repository;

import java.util.List;

import br.com.siqueira.category.domain.enums.CategoryType;
import br.com.siqueira.category.domain.model.Category;
import br.com.siqueira.category.infrastructure.persistence.entity.CategoryEntity;
import br.com.siqueira.category.infrastructure.persistence.mapper.CategoryMapper;
import br.com.siqueira.shared.api.error.ApiErrorCode;
import br.com.siqueira.shared.api.error.ApiException;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoryRepository implements PanacheRepositoryBase<CategoryEntity, Long> {

    public List<Category> getCategories() {
        return findAll()
                .stream()
                .map(CategoryMapper::toModel)
                .toList();
    }

    public Category getCategoryById(Long id) {
        CategoryEntity entity = findById(id);
        if (entity == null) {
            throw new ApiException(ApiErrorCode.CATEGORY_NOT_FOUND);
        }
        return CategoryMapper.toModel(entity);
    }

    public Category save(Category category) {
        if (category.getId() == null) {
            CategoryEntity entity = CategoryMapper.toEntity(category);
            persist(entity);
            flush();
            return CategoryMapper.toModel(entity);
        }
        CategoryEntity entity = findById(category.getId());
        if (entity == null) {
            return null;
        }
        entity.setName(category.getName());
        entity.setType(category.getType().name());
        entity.setDescription(category.getDescription());
        entity.setActive(category.isActive());
        persist(entity);
        flush();
        return CategoryMapper.toModel(entity);
    }

    public boolean existsByNameAndType(String name, CategoryType type) {
        return find(
                "lower(trim(name)) = ?1 and type = ?2",
                name.trim().toLowerCase(),
                type.name())
                .firstResultOptional()
                .isPresent();
    }

}
