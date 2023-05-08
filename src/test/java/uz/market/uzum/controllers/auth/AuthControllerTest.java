package uz.market.uzum.controllers.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import uz.market.uzum.services.auth.AuthService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@SpringBootTest(classes = AuthController.class)
//@AutoConfigureMockMvc
@WithMockUser
@WebMvcTest(controllers = AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void generateToken() {
    }

    @Test
    void refreshToken() {
    }

    @Test
    void register() {
    }

    @Test
    void addRole() throws Exception {

/*
        when(authService.addRole(1L, 1)).thenReturn("Role added");

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/auth/addRole")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(1L))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").value("Role added"))
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        assertThat(contentAsString).isEqualTo("Role added");
        verify(authService, times(1)).addRole(any(), any());*/

    }
}