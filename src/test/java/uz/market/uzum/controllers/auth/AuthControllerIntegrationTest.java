package uz.market.uzum.controllers.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import uz.market.uzum.UzumMarketApplication;
import uz.market.uzum.domains.user.User;
import uz.market.uzum.dtos.auth.RefreshTokenRequest;
import uz.market.uzum.dtos.auth.TokenRequest;
import uz.market.uzum.dtos.auth.TokenResponse;
import uz.market.uzum.dtos.auth.UserCreateDTO;
import uz.market.uzum.services.auth.AuthService;

import java.util.Date;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = UzumMarketApplication.class)
@AutoConfigureMockMvc
@WithMockUser
public class AuthControllerIntegrationTest {

    private static final String TEST_ACCESS_TOKEN = "7134743777217A25432A462D4A614E6452675";
    private static final String TEST_REFRESH_TOKEN = "7134743777217A25";


    @MockBean
    private AuthService authService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Disabled
    @Test
    void testGenerateToken() throws Exception {
        TokenRequest tokenRequest = new TokenRequest("username", "password");
        TokenResponse tokenResponse = new TokenResponse(TEST_ACCESS_TOKEN, new Date(6000000), TEST_REFRESH_TOKEN, new Date(864000000));

        when(authService.generateToken(any())).thenReturn(tokenResponse);

        mockMvc.perform(post("/api/v1/auth/access/token")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(tokenRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value(tokenResponse.getAccessToken()))
                .andExpect(jsonPath("$.refreshToken").value(tokenResponse.getRefreshToken()));

        verify(authService).generateToken(any());
        verifyNoMoreInteractions(authService);
    }

    @Disabled
    @Test
    void testRefreshToken() throws Exception {
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest("refresh_token");
        TokenResponse tokenResponse = new TokenResponse(TEST_ACCESS_TOKEN, new Date(6000000), TEST_REFRESH_TOKEN, new Date(864000000));

        when(authService.refreshToken(refreshTokenRequest)).thenReturn(tokenResponse);

        mockMvc.perform(post("/api/v1/auth/refresh/token")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(refreshTokenRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value(tokenResponse.getAccessToken()))
                .andExpect(jsonPath("$.refreshToken").value(tokenResponse.getRefreshToken()));

        verify(authService).refreshToken(refreshTokenRequest);
        verifyNoMoreInteractions(authService);
    }


    @Disabled
    @Test
    void testRegister() throws Exception {
        UserCreateDTO userCreateDTO = new UserCreateDTO("string123", "javohir", "testuser", "javokhir544gmail.com");

        User user = new User();
        user.setId(1L);
        user.setEmail("javokhir544@gmail.com");

        when(authService.create(userCreateDTO)).thenReturn(user);

        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(userCreateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("testuser"));

        verify(authService).create(userCreateDTO);
        verifyNoMoreInteractions(authService);
    }

    @WithMockUser(roles = {"SUPER_ADMIN", "ADMIN"})
    @Test
    void testAddRole() throws Exception {
        Long userId = 1L;
        Integer roleId = 2;

        when(authService.addRole(userId, roleId)).thenReturn("Role added successfully");

        mockMvc.perform(post("/api/v1/auth/addRole")
                        .param("userId", userId.toString())
                        .param("roleId", roleId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("Role added successfully"));

        verify(authService).addRole(userId, roleId);
        verifyNoMoreInteractions(authService);
    }
}

