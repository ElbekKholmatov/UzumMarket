package uz.market.uzum.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.market.uzum.domains.Document;
import uz.market.uzum.domains.product.Product;
import uz.market.uzum.dtos.product.ProductCreateDTO;
import uz.market.uzum.dtos.product.ProductUpdateDTO;
import uz.market.uzum.services.ProductService;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
@Tag(name = "Product", description = "Product API")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "This API used for creating a product", responses = {
            @ApiResponse(responseCode = "201", description = "Product created"),
            @ApiResponse(responseCode = "400", description = "Product not created")})
    @PostMapping(value = "/create")
    public ResponseEntity<Product> createProduct(@Valid ProductCreateDTO dto) {
        Product product = productService.createProduct(dto);
        return ResponseEntity.status(201).body(product);
    }

    @Operation(summary = "This API used for adding pictures to a product", responses = {
            @ApiResponse(responseCode = "201", description = "Image added"),
            @ApiResponse(responseCode = "400", description = "Images are not added")})
    @PostMapping(value = "/addImage")
    public ResponseEntity<List<Document>> addImagesToProduct(Integer productId, List<MultipartFile> files) {
        List<Document> documents = productService.addImages(productId, files);
        return ResponseEntity.ok(documents);
    }


    @Operation(summary = "This API used for getting a product by id", responses = {
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "Product not found")})
    @GetMapping(value = "/byId")
    public ResponseEntity<Product> getProductById(Integer id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok().body(product);
    }

    @Operation(summary = "This api get all products by category id", responses = {
            @ApiResponse(responseCode = "200", description = "Categories found"),
            @ApiResponse(responseCode = "404", description = "Products not found")})
    @GetMapping(value = "/getAll/{categoryID}")
    public ResponseEntity<List<Product>> getAllProducts(@PathVariable Integer categoryID) {
        List<Product> products = productService.getCategoryProducts(categoryID);
        return ResponseEntity.ok().body(products);
    }


    @Operation(summary = "This API used for deleting a product by id", responses = {
            @ApiResponse(responseCode = "204", description = "Product deleted"),
            @ApiResponse(responseCode = "404", description = "Product not found")})
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteProduct(Integer id) {
        boolean productID = productService.deleteProduct(id);
        return ResponseEntity.status(204).body(productID);
    }


    @Operation(summary = "This API used for updating a product", responses = {
            @ApiResponse(responseCode = "204", description = "Product updated"),
            @ApiResponse(responseCode = "404", description = "Product not found")})
    @PutMapping(value = "/update")
    public ResponseEntity<Product> updateProduct(@Valid ProductUpdateDTO dto) {
        Product product = productService.updateProduct(dto);
        return ResponseEntity.status(204).body(product);
    }


}
