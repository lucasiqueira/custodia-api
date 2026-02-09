package br.com.siqueira.category.infrastructure.persistence.repository;

import java.util.List;

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
    
}
