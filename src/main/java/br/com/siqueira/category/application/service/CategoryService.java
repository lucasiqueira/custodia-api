package br.com.siqueira.category.application.service;

import java.util.List;

import br.com.siqueira.category.domain.model.Category;
import br.com.siqueira.category.infrastructure.persistence.repository.CategoryRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.getCategories();
    }
    
}
