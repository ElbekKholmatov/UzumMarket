package uz.market.uzum.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import uz.market.uzum.domains.product.Category;
import uz.market.uzum.dtos.category.CategoryCreateDTO;
import uz.market.uzum.services.CategoryService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CategoryService categoryService;

    @Test
    public void createCategoryTest() throws Exception {
        CategoryCreateDTO categoryCreateDTO = new CategoryCreateDTO();
        categoryCreateDTO.setName("Test Category");
        categoryCreateDTO.setParentID(null);
        Category category = new Category();
        category.setName(categoryCreateDTO.getName());

//        given(categoryService.createCategory(Mockito.any(CategoryCreateDTO.class))).willReturn(category);
        when(categoryService.createCategory(any())).thenReturn(Category.childBuilder().id(1).build());
        mockMvc.perform(post("/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(categoryCreateDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", Matchers.is("Test Category")))
                .andExpect(jsonPath("$.parentID", Matchers.is(null)));
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createCategory() {
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