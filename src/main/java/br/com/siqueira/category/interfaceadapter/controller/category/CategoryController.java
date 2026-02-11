package br.com.siqueira.category.interfaceadapter.controller.category;

import java.net.URI;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;

import br.com.siqueira.category.application.dto.request.CreateCategoryRequest;
import br.com.siqueira.category.application.dto.request.UpdateCategoryRequest;
import br.com.siqueira.category.application.dto.response.CategoryResponse;
import br.com.siqueira.category.application.service.CategoryService;
import br.com.siqueira.category.domain.model.Category;
import br.com.siqueira.category.interfaceadapter.controller.mapper.CategoryRequestMapper;
import br.com.siqueira.category.interfaceadapter.controller.mapper.CategoryResponseMapper;
import br.com.siqueira.shared.api.openapi.responses.BadRequestResponse;
import br.com.siqueira.shared.api.openapi.responses.ConflictResponse;
import br.com.siqueira.shared.api.openapi.responses.StandardErrorResponses;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
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

    @POST
    @APIResponse(responseCode = "201", description = "Category created successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = CategoryResponse.class)))
    @BadRequestResponse
    @ConflictResponse
    public RestResponse<CategoryResponse> createCategory(@Valid CreateCategoryRequest request) {

        Category categoryRequest = CategoryRequestMapper.toNewCategory(request);
        Category created = categoryService.createCategory(categoryRequest);
        
        CategoryResponse response = CategoryResponseMapper.from(created);
        
        return RestResponse.ResponseBuilder
                .create(RestResponse.Status.CREATED, response)
                .location(URI.create("/v1/categories/" + response.id()))
                .build();
    }

    @PUT
    @Path("{id}")
    @APIResponse(responseCode = "200", description = "Category updated successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = CategoryResponse.class)))
    @StandardErrorResponses
    public RestResponse<CategoryResponse> updateCategory(@PathParam("id") Long id, @Valid UpdateCategoryRequest request) {
        Category updated = categoryService.updateCategory(id, request.name(), request.type(), request.description());
        CategoryResponse response = CategoryResponseMapper.from(updated);
        return RestResponse.ok(response); 
    }
}