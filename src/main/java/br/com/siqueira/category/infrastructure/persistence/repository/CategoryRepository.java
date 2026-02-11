package br.com.siqueira.category.infrastructure.persistence.repository;

import java.util.List;

import br.com.siqueira.category.domain.enums.CategoryType;
import br.com.siqueira.category.domain.model.Category;
import br.com.siqueira.category.infrastructure.persistence.entity.CategoryEntity;
import br.com.siqueira.category.infrastructure.persistence.mapper.CategoryMapper;
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

    public Category save(Category category) {
        if (category.getId() == null) {
            CategoryEntity entity = CategoryMapper.toEntity(category);
            persist(entity);
            flush();
            return CategoryMapper.toModel(entity);
        }
        return null;
    }

    public boolean existsByNameAndType(String name, CategoryType type) {
        return find("name = ?1 and type = ?2", name, type.name()).firstResultOptional().isPresent();
    }
    
}
