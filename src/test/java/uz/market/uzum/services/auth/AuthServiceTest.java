package uz.market.uzum.services.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uz.market.uzum.configuration.jwt.JwtUtils;
import uz.market.uzum.domains.user.User;
import uz.market.uzum.domains.user.UserRole;
import uz.market.uzum.dtos.auth.RefreshTokenRequest;
import uz.market.uzum.dtos.auth.TokenRequest;
import uz.market.uzum.dtos.auth.TokenResponse;
import uz.market.uzum.enums.TokenType;
import uz.market.uzum.exceptions.ItemNotFoundException;
import uz.market.uzum.repositories.BasketRepository;
import uz.market.uzum.repositories.user.UserRepository;
import uz.market.uzum.repositories.user.UserRolesRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {AuthService.class})
@ExtendWith(SpringExtension.class)
public class AuthServiceTest {


    public static final Date ACCESS_TOKEN_EXPIRY = new Date(6000000);
    public static final Date REFRESH_TOKEN_EXPIRY = new Date(864000000);
    public static final String USER_TEST_EMAIL = "test@example.com";
    public static final String PASSWORD = "password123";
    public static final Long TEST_USER_ID = 1L;
    public static final Integer TEST_ROLE_ID = 1;

    @MockBean
    private AuthenticationManager authenticationManager;


    @MockBean
    private UserRepository userRepository;

    @MockBean
    private JwtUtils jwtUtils;

    @MockBean
    private UserRolesRepository userRolesRepository;

    @MockBean
    private BasketRepository basketRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;
    private AuthService authService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.authService = new AuthService(
                authenticationManager,
                userRepository,
                passwordEncoder,
                jwtUtils,
                userRolesRepository,
                basketRepository
        );
    }

    @Test
    public void testGenerateTokenWithValidCredentials() {

        TokenRequest tokenRequest = new TokenRequest(USER_TEST_EMAIL, PASSWORD);

        when(userRepository.findByEmailLike(USER_TEST_EMAIL)).thenReturn(Optional.of(new User()));

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(USER_TEST_EMAIL, PASSWORD);
        when(authenticationManager.authenticate(authentication)).thenReturn(authentication);

        TokenResponse expectedTokenResponse = new TokenResponse(ACCESS_TOKEN_EXPIRY, REFRESH_TOKEN_EXPIRY);

        when(jwtUtils.generateToken(USER_TEST_EMAIL)).thenReturn(expectedTokenResponse);

        TokenResponse actualTokenResponse = authService.generateToken(tokenRequest);

        assertEquals(expectedTokenResponse, actualTokenResponse);

        verify(userRepository, times(1)).findByEmailLike(USER_TEST_EMAIL);
        verify(authenticationManager, times(1)).authenticate(authentication);
        verify(jwtUtils, times(1)).generateToken(USER_TEST_EMAIL);
    }

    @Test
    public void testGenerateTokenWithInvalidCredentials() {
        String email = USER_TEST_EMAIL;

        TokenRequest tokenRequest = new TokenRequest(email, PASSWORD);

        when(userRepository.findByEmailLike(email)).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class, () ->
                authService.generateToken(tokenRequest));

        verify(userRepository, times(1)).findByEmailLike(email);
        verify(authenticationManager, never()).authenticate(any());
        verify(jwtUtils, never()).generateToken(any());
    }


    @Test
    void testRefreshTokenWhenTokenInvalid() {
        String refreshToken = "invalid_token";
        when(jwtUtils.isTokenValid(refreshToken, TokenType.REFRESH)).thenReturn(false);
        assertThrows(CredentialsExpiredException.class, () -> authService.refreshToken(new RefreshTokenRequest(refreshToken)));
    }

    @Test
    public void addRole_roleNotFound_throwItemNotFoundException() {
        when(userRolesRepository.findById(TEST_ROLE_ID)).thenReturn(Optional.empty());
        assertThrows(ItemNotFoundException.class, () -> authService.addRole(TEST_USER_ID, TEST_ROLE_ID));
    }

    @Test
    public void addRole_userNotFound_throwItemNotFoundException() {
        Long userId = 1L;
        Integer roleId = 1;
        UserRole userRole = UserRole.builder().id(roleId).build();

        when(userRolesRepository.findById(roleId)).thenReturn(Optional.of(userRole));
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(ItemNotFoundException.class, () -> authService.addRole(userId, roleId));
    }

    @Test
    public void addRole_roleAndUserFound_addRoleToUser() {
        Long userId = 1L;
        Integer roleId = 1;
        UserRole userRole = UserRole.builder().id(roleId).build();
        User user = User.builder().id(userId).roles(new ArrayList<>()).build();

        when(userRolesRepository.findById(roleId)).thenReturn(Optional.of(userRole));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        String result = authService.addRole(userId, roleId);
        assertEquals("Role added", result);
        Collection<UserRole> roles = user.getRoles();
        assertEquals(1, roles.size());
        assertEquals(userRole, roles.iterator().next());
    }

}