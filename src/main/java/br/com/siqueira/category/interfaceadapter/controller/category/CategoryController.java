package br.com.siqueira.category.interfaceadapter.controller.category;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;

import br.com.siqueira.category.application.dto.response.CategoryResponse;
import br.com.siqueira.category.application.service.CategoryService;
import br.com.siqueira.category.interfaceadapter.controller.mapper.CategoryResponseMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
@Tag(name = "Category", description = "Category management endpoints")
@Path("v1/categories")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GET
    @APIResponse(responseCode = "200", description = "List of categories", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.ARRAY, implementation = CategoryResponse.class)))
    public RestResponse<List<CategoryResponse>> getAllCategories() {
        return RestResponse.ok(CategoryResponseMapper.from(categoryService.getAllCategories()));
    }
}
