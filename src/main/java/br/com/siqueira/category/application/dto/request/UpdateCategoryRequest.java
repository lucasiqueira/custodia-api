package br.com.siqueira.category.application.dto.request;

import br.com.siqueira.category.domain.enums.CategoryType;
import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;

public record UpdateCategoryRequest(
                @NotBlank String name,
                @NotNull CategoryType type,
                String description) {

}
