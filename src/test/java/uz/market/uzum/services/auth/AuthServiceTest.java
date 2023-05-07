/*
package uz.market.uzum.services.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uz.market.uzum.domains.user.User;
import uz.market.uzum.enums.UserStatus;
import uz.market.uzum.exceptions.DublicateElementException;
import uz.market.uzum.repositories.user.UserRepository;
import uz.market.uzum.services.user.UserService;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    private static final String REGISTRATION_USER_GMAIL_ADDRESS = "javokhir544@gmail.com";
    private static final String REGISTRATION_USER_FIRST_NAME = "Javokhir";
    public static final String REGISTRATION_USER_PASSWORD = "123";
    UserService userService;
    AuthService authService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    void generateToken() {


    }

    @Test
    void registerNewUserAccount() {
        User user = User.builder()
                .id(1L)
                .email(REGISTRATION_USER_GMAIL_ADDRESS)
                .password(REGISTRATION_USER_PASSWORD)
                .status(UserStatus.ACTIVE)
                .firstName(REGISTRATION_USER_FIRST_NAME)
                .build();
        when(userRepository.save(any(User.class))).thenReturn(User.builder().id(1L).build());
        User actual = userRepository.save(user);
        assertEquals("The saved user should have the expected 1", 1L, actual.getId());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void registerNewUserAccountWithNullData() {
        when(userRepository.save(null))
                .thenThrow(new IllegalArgumentException("User cannot be null"));
        assertThrows(RuntimeException.class, () -> {
            userRepository.save(null);
        }).printStackTrace();
        verify(userRepository, times(1)).save(null);
    }

    @Test
    void registerNewUserAccountDublicate_ShouldFieldsThrowException() throws DublicateElementException {
        when(userRepository.save(User.builder().email(REGISTRATION_USER_GMAIL_ADDRESS).build()))
                .thenThrow(new DublicateElementException("User with this email already exists"));

        assertThrows(DublicateElementException.class, () -> {
            userRepository.save(User.builder().email(REGISTRATION_USER_GMAIL_ADDRESS).build());
            verify(userRepository, times(1)).save(User.builder().email(REGISTRATION_USER_GMAIL_ADDRESS).build());
        });
    }


    @Test
    void refreshToken() {
    }
}*/
