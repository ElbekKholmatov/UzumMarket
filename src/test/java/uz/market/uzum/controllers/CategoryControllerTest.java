package uz.market.uzum.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import uz.market.uzum.domains.product.Category;
import uz.market.uzum.services.CategoryService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CategoryController.class)
class CategoryControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    CategoryService categoryService;

    @Test
    void createCategory() throws Exception {
        when(categoryService.createCategory(any())).thenReturn(Category.childBuilder().id(1).build());
        Category category = Category.childBuilder()
                .name("forTest")
                .build();
        mockMvc.perform(post("/api/v1/categories/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.id").value(1))
        ;
    }

    @Test
    void getCategoryById() {
    }

    @Test
    void getAllCategory() {
    }

    @Test
    void deleteCategory() {
    }

    @Test
    void updateCategory() {
    }
}