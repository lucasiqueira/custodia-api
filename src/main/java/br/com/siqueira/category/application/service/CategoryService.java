package br.com.siqueira.category.application.service;

import java.util.List;

import br.com.siqueira.category.domain.enums.CategoryType;
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
        validateUniqueNameAndType(category.getName(), category.getType());

        return categoryRepository.save(category);
    }

    @Transactional
    public Category updateCategory(Long id, String name, CategoryType type, String description) {
        validateUniqueNameAndType(name, type);
        Category category = this.load(id);
        category.update(name, type, description);
        return categoryRepository.save(category);
    }

    private Category load(Long id) {
        return categoryRepository.getCategoryById(id);
    }

    private void validateUniqueNameAndType(String name, CategoryType type) {
        if (categoryRepository.existsByNameAndType(name, type)) {
            String message = "Category with name '" + name + "' and type '" + type.name() + "' already exists";
            throw new ApiException(ApiErrorCode.CATEGORY_ALREADY_EXISTS, message);
        }
    }
    
}
