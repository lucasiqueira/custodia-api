package br.com.siqueira.category.application.dto.request;

import br.com.siqueira.category.domain.enums.CategoryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateCategoryRequest {
    @NotBlank
    @Size(max = 50)
    public String name;

    @NotNull
    public CategoryType type;

    @Size(max = 255)
    public String description;
}
