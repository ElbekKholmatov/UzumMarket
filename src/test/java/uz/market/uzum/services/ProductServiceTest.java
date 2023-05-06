package uz.market.uzum.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uz.market.uzum.domains.product.Category;
import uz.market.uzum.domains.product.Product;
import uz.market.uzum.dtos.category.CategoryCreateDTO;
import uz.market.uzum.dtos.product.ProductCreateDTO;
import uz.market.uzum.dtos.product.ProductUpdateDTO;
import uz.market.uzum.enums.ProductStatus;
import uz.market.uzum.exceptions.ItemNotFoundException;
import uz.market.uzum.mappers.product.OrderMapper;
import uz.market.uzum.repositories.CategoryRepository;
import uz.market.uzum.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ProductService.class})
@ExtendWith(SpringExtension.class)
class ProductServiceTest {
    @MockBean
    private CategoryService categoryService;
    @MockBean
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @Test
    void createProduct() {
        Category category = Category.childBuilder().id(2).build();
        Product product = Product.childBuilder().id(1).build();
        when(categoryService.getCategoryById(2)).thenReturn(category);
        when(productRepository.save(any())).thenReturn(product);
        ProductCreateDTO dto = ProductCreateDTO.builder()
                .name("p1")
                .categoryID(2)
                .count(4)
                .description("zo'r")
                .status(ProductStatus.ACTIVE)
                .price(1233D)
                .discount(0.0)
                .build();
        Product actual = productService.createProduct(dto);
        assertEquals(1, actual.getId());
        verify(categoryService, times(1)).getCategoryById(any());
        verify(productRepository, times(1)).save(any());
    }

    @Test
    void createProductShouldThrowException() {
        Category category = Category.childBuilder().id(2).build();
        Product product = Product.childBuilder().id(1).build();
        when(productRepository.save(any())).thenReturn(product);
        when(categoryService.getCategoryById(2)).thenReturn(category);
        ProductCreateDTO dto = ProductCreateDTO.builder()
                .name("p1")
                .categoryID(1)
                .count(4)
                .description("zo'r")
                .status(ProductStatus.ACTIVE)
                .price(1233D)
                .discount(0.0)
                .build();
        assertThrows(ItemNotFoundException.class, () -> productService.createProduct(dto)).printStackTrace();
    }

    @Test
    void getProductById() {
        Product product = Product.childBuilder().id(2).build();
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        Product actual = productService.getProductById(1);
        assertEquals(2, actual.getId());
    }

    @Test
    void getProductByIdShouldThrowException() {
        Product product = Product.childBuilder().id(2).build();
        when(productRepository.findById(2)).thenReturn(Optional.of(product));
        assertThrows(ItemNotFoundException.class, () -> productService.getProductById(1)).printStackTrace();
    }

    @Test
    void getCategoryProducts() {
        List<Product> productList = List.of(Product.childBuilder().id(1).build());
        when(productRepository.findByCategoryId(2)).thenReturn(productList);
        when(categoryService.getCategoryById(2)).thenReturn(Category.childBuilder().id(2).build());
        assertEquals(1, productService.getCategoryProducts(2).size());
    }

    @Test
    void getCategoryProductsShouldThrowException() {
        List<Product> productList = List.of(Product.childBuilder().id(1).build());
        when(productRepository.findByCategoryId(2)).thenReturn(productList);
        assertThrows(ItemNotFoundException.class, () -> productService.getCategoryProducts(1)).printStackTrace();
    }

    @Test
    void deleteProduct() {
        when(productRepository.updateIsDeletedById(1)).thenReturn(1);
        assertTrue(productService.deleteProduct(1));
    }

    @Test
    void deleteProductShouldReturnFalse() {
        when(productRepository.updateIsDeletedById(1)).thenReturn(1);
        assertFalse(productService.deleteProduct(11));
    }

    @Test
    void updateProduct() {
        Product product = Product.childBuilder().id(1).build();
        when(productRepository.save(any())).thenReturn(product);
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        when(categoryService.getCategoryById(2)).thenReturn(Category.childBuilder().id(2).build());
        ProductUpdateDTO dto = ProductUpdateDTO.builder()
                .productID(1)
                .categoryID(2)
                .description("123")
                .discount(1.1)
                .build();
        Product actual = productService.updateProduct(dto);
        assertEquals(1, actual.getId());

    }
  @Test
    void updateProductShouldThrowException() {
        Product product = Product.childBuilder().id(1).build();
        when(productRepository.save(any())).thenReturn(product);
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        when(categoryService.getCategoryById(2)).thenReturn(Category.childBuilder().id(2).build());
        ProductUpdateDTO dto = ProductUpdateDTO.builder()
                .productID(2)
                .categoryID(1)
                .description("123")
                .discount(1.1)
                .build();
        assertThrows(ItemNotFoundException.class, () -> productService.updateProduct(dto)).printStackTrace();

    }

    @Test
    void addImages() {
    }
}