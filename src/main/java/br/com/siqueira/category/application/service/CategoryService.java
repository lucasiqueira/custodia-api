package br.com.siqueira.category.application.service;

import java.util.List;

import br.com.siqueira.category.domain.model.Category;
import br.com.siqueira.category.infrastructure.persistence.repository.CategoryRepository;
import br.com.siqueira.shared.api.error.ApiErrorCode;
import br.com.siqueira.shared.api.error.ApiException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.getCategories();
    }

    @Transactional
    public Category createCategory(Category category) {

        if (categoryRepository.existsByNameAndType(category.getName(), category.getType())) {
            String message = "Category with name '" + category.getName() + "' and type '" + category.getType().name() + "' already exists";
            throw new ApiException(ApiErrorCode.CATEGORY_ALREADY_EXISTS, message);
        }

        return categoryRepository.save(category);
    }
    
}
