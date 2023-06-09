package uz.market.uzum.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uz.market.uzum.domains.product.Category;
import uz.market.uzum.dtos.category.CategoryCreateDTO;
import uz.market.uzum.dtos.category.CategoryUpdateDTO;
import uz.market.uzum.exceptions.ItemNotFoundException;
import uz.market.uzum.repositories.CategoryRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CategoryService.class})
@ExtendWith(SpringExtension.class)
class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;
    @MockBean
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createCategory() {
        Category category = Category.childBuilder().id(1).build();
        when(categoryRepository.save(any())).thenReturn(category);
        Category actual = categoryService.createCategory(CategoryCreateDTO.builder()
                .name("Test")
                .build());
        assertEquals(1, actual.getId());
        verify(categoryRepository, times(1)).save(any());
    }

    @Test
    void getCategoryById() {
        Category category = Category.childBuilder().id(2).build();
        when(categoryRepository.getCategoryById(2)).thenReturn(Optional.of(category));
        Category actual = categoryService.getCategoryById(2);
        assertEquals(2, actual.getId());
    }

    @Test
    void getCategoryByIdShouldThrowException() {
        Category category = Category.childBuilder().id(2).build();
        when(categoryRepository.getCategoryById(2)).thenReturn(Optional.of(category));
        assertThrows(ItemNotFoundException.class, () -> categoryService.getCategoryById(1)).printStackTrace();
    }

    @Test
    void deleteCategory() {
        Category category = Category.childBuilder().id(2).build();
        when(categoryRepository.getCategoryById(2)).thenReturn(Optional.of(category));
        assertTrue(categoryService.deleteCategory(2));
        verify(categoryRepository, atLeastOnce()).save(any());
    }

    @Test
    void deleteCategoryShouldThrowException() {
        Category category = Category.childBuilder().id(2).build();
        when(categoryRepository.getCategoryById(2)).thenReturn(Optional.of(category));
        assertThrows(ItemNotFoundException.class, () -> categoryService.deleteCategory(1));
    }

    @Test
    void updateCategory() {
        Category category = Category.childBuilder().id(2).name("Test").build();
        when(categoryRepository.getCategoryById(2)).thenReturn(Optional.of(category));
        CategoryUpdateDTO dto = CategoryUpdateDTO.builder()
                .categoryID(2)
                .name("123")
                .build();
        category.setName(dto.getName());
        when(categoryRepository.save(any())).thenReturn(category);
        Category actual = categoryService.updateCategory(dto);
        assertEquals(2, actual.getId());
        assertEquals("123", actual.getName());
//        verify(categoryRepository, atLeastOnce()).save(any());
    }

    @Test
    void getAll() {
    }
}