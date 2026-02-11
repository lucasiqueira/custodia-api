package br.com.siqueira.category.interfaceadapter.controller.mapper;

import br.com.siqueira.category.application.dto.request.CreateCategoryRequest;
import br.com.siqueira.category.domain.model.Category;

public class CategoryRequestMapper {
    private CategoryRequestMapper() {
    }

    public static Category toNewCategory(CreateCategoryRequest request) {
        return Category.createNew(request.name, request.type, request.description);
    }
}
