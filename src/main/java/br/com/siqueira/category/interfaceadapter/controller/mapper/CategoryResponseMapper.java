package br.com.siqueira.category.interfaceadapter.controller.mapper;

import java.time.format.DateTimeFormatter;
import java.util.List;

import br.com.siqueira.category.application.dto.response.CategoryResponse;
import br.com.siqueira.category.domain.model.Category;

public class CategoryResponseMapper {

    private static final DateTimeFormatter ISO = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    
    public static CategoryResponse from (Category model) {
        return new CategoryResponse(
                model.getId(),
                model.getName(),
                model.getType().name(),
                model.getDescription(),
                model.isActive(),
                model.getCreatedAt().format(ISO),
                model.getUpdatedAt().format(ISO)
        );
    }

    public static List<CategoryResponse> from(List<Category> models) {
        return models.stream()
                .map(CategoryResponseMapper::from)
                .toList();
    }
}
