package uz.market.uzum.repositories.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uz.market.uzum.domains.user.User;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

    private static final String USER_EMAIL = "javokhir544@example.com";
    @Mock
    private UserRepository userRepository;

    @Test
    void testCheckUniqueFields() {
        // Mock the behavior of the userRepository
        User user = new User();
        user.setEmail(USER_EMAIL);
        when(userRepository.checkUniqueFields(USER_EMAIL)).thenReturn(Optional.of(user));

        // Call the method being tested
        Optional<User> result = userRepository.checkUniqueFields(USER_EMAIL);

        // Verify the result
        assertEquals(user, result.get());

        // Verify that the userRepository's method was called with the correct parameter
        verify(userRepository).checkUniqueFields(USER_EMAIL);
    }

    @Test
    void testFindByEmail() {
        // Mock the behavior of the userRepository
        User user = new User();
        user.setEmail(USER_EMAIL);
        when(userRepository.findByEmail(USER_EMAIL)).thenReturn(user);

        // Call the method being tested
        User result = userRepository.findByEmail(USER_EMAIL);

        // Verify the result
        assertEquals(user, result);

        // Verify that the userRepository's method was called with the correct parameter
        verify(userRepository).findByEmail(USER_EMAIL);
    }

    @Test
    void testFindByEmailLike() {
        // Mock the behavior of the userRepository
        User user = new User();
        user.setEmail(USER_EMAIL);
        when(userRepository.findByEmailLike("javokhir544%")).thenReturn(Optional.of(user));

        // Call the method being tested
        Optional<User> result = userRepository.findByEmailLike("javokhir544%");

        // Verify the result
        assertEquals(user, result.get());

        // Verify that the userRepository's method was called with the correct parameter
        verify(userRepository).findByEmailLike("javokhir544%");
    }

    @Test
    void testFindAllUserDetails() {
        // Mock the behavior of the userRepository
        User user = new User();
        user.setEmail(USER_EMAIL);
        when(userRepository.findAllUserDetails()).thenReturn(Optional.of(Collections.singletonList(user)));

        // Call the method being tested
        Optional<Collection<User>> result = userRepository.findAllUserDetails();

        // Verify the result
        assertEquals(user, result.get().iterator().next());

        // Verify that the userRepository's method was called with the correct parameter
        verify(userRepository).findAllUserDetails();
    }
}
