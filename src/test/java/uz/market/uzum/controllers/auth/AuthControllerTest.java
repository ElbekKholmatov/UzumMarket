package uz.market.uzum.controllers.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uz.market.uzum.dtos.auth.TokenResponse;
import uz.market.uzum.services.auth.AuthService;

import java.util.Date;

public class AuthControllerTest {

    private static final Date ACCESS_TOKEN_EXPIRY = new Date(6000000);
    private static final Date REFRESH_TOKEN_EXPIRY = new Date(864000000);
    private static final TokenResponse EXPECTED_TOKEN_RESPONSE = new TokenResponse(ACCESS_TOKEN_EXPIRY, REFRESH_TOKEN_EXPIRY);
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @MockBean
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
        objectMapper = new ObjectMapper();
    }

}
