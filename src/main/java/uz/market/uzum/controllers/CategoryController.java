package uz.market.uzum.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.market.uzum.domains.product.Category;
import uz.market.uzum.dtos.category.CategoryCreateDTO;
import uz.market.uzum.dtos.category.CategoryUpdateDTO;
import uz.market.uzum.services.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
@Tag(name = "Category", description = "Category API")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "This API used for creating a category", responses = {
            @ApiResponse(responseCode = "201", description = "Category created"),
            @ApiResponse(responseCode = "400", description = "Category not created")})
    @PostMapping(value = "/create")
    public ResponseEntity<Category> createCategory(@Valid CategoryCreateDTO dto) {
        Category category = categoryService.createCategory(dto);
        return ResponseEntity.status(201).body(category);
    }


    @Operation(summary = "This API used for getting a category by id", responses = {
            @ApiResponse(responseCode = "200", description = "Category found"),
            @ApiResponse(responseCode = "404", description = "Category not found")})
    @GetMapping(value = "/get")
    public ResponseEntity<Category> getCategoryById(Integer id) {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @Operation(summary = "This API used for getting all categories", responses = {
            @ApiResponse(responseCode = "200", description = "Categories found"),
            @ApiResponse(responseCode = "404", description = "Categories not found")})
    @GetMapping(value = "/getAll")
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }

    @Operation(summary = "This API used for deleting a category by id",
            responses = {@ApiResponse(responseCode = "200", description = "Category deleted"),
                    @ApiResponse(responseCode = "404", description = "Category not found")})
    @DeleteMapping(produces = "application/json")
    public ResponseEntity<Void> deleteCategory(Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "This API used for updating a category",
            responses = {@ApiResponse(responseCode = "200", description = "Category updated"),
                    @ApiResponse(responseCode = "404", description = "Category not found")})
    @PutMapping(value = "/update")
    public ResponseEntity<Category> updateCategory(@Valid CategoryUpdateDTO dto) {
        Category category = categoryService.updateCategory(dto);
        return ResponseEntity.status(200).body(category);
    }


}
