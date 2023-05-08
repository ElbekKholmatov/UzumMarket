package uz.market.uzum.repositories.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uz.market.uzum.domains.user.UserRole;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRolesRepositoryTest {

    private static final String USER_ADMIN_ROLE = "ADMIN";
    @Mock
    private UserRolesRepository userRolesRepository;

    @Test
    void testFindByCode() {
        UserRole userRole = UserRole.builder().code(USER_ADMIN_ROLE).build();
        when(userRolesRepository.findByCode(USER_ADMIN_ROLE)).thenReturn(userRole);

        UserRole result = userRolesRepository.findByCode(USER_ADMIN_ROLE);

        assertEquals(userRole, result);

        verify(userRolesRepository).findByCode(USER_ADMIN_ROLE);
    }
}
