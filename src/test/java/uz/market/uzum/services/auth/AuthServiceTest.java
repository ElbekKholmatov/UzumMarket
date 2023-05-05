package uz.market.uzum.services.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uz.market.uzum.domains.user.User;
import uz.market.uzum.enums.UserStatus;
import uz.market.uzum.repositories.user.UserRepository;
import uz.market.uzum.services.user.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    UserService userService;

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
    void create() {
        User user = User.builder()
                .id(1L)
                .phoneNumber("+998995110899")
                .email("javokhir544@gmail.com")
                .password("123")
                .status(UserStatus.ACTIVE)
                .firstName("Javokhir")
                .build();
        when(userRepository.save(any(User.class))).thenReturn(user);
        User actual = userRepository.save(user);
        assertThat(actual.getId().equals(2L));
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void refreshToken() {
    }
}