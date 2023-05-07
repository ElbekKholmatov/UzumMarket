package uz.market.uzum.services.roles;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uz.market.uzum.domains.user.UserPermission;
import uz.market.uzum.domains.user.UserRole;
import uz.market.uzum.dtos.roles.UserRoleCreateDTO;
import uz.market.uzum.dtos.roles.UserRolePermissionDTO;
import uz.market.uzum.repositories.user.UserRolesRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = UserRoleService.class)
@ExtendWith(SpringExtension.class)
class UserRoleServiceTest {


    public static final String UPDATE_ROLE = "UPDATE_ROLE";
    private static final String ROLE_CODE = "USER";
    private static final String ROLE_NAME = "User role";
    private static final String UPDATE_ROLE_NAME = "Update role name";
    @Autowired
    UserRoleService userRoleService;

    @MockBean
    UserPermissionService userPermissionService;

    @MockBean
    UserRolesRepository userRolesRepository;

    @Test
    void save() {
        UserRoleCreateDTO userRoleCreateDTO = new UserRoleCreateDTO(ROLE_NAME, ROLE_CODE);

        int userId = new Random().nextInt(0, 100000);
        UserRole savedUserRole = new UserRole(userId, ROLE_NAME, ROLE_CODE, Collections.emptyList());

        when(userRolesRepository.save(any())).thenReturn(savedUserRole);
        UserRole actualSavedUserRole = userRoleService.save(userRoleCreateDTO);
        assertEquals(userId, actualSavedUserRole.getId());
        assertEquals(ROLE_CODE, actualSavedUserRole.getCode());
        assertEquals(ROLE_NAME, actualSavedUserRole.getName());
    }

    @Test
    void update() {
        UserRole userRole = new UserRole(2, ROLE_NAME, ROLE_CODE, Collections.emptyList());
        UserRoleCreateDTO userRoleUpdateDTO = new UserRoleCreateDTO(UPDATE_ROLE_NAME, UPDATE_ROLE);

        when(userRolesRepository.findById(2)).thenReturn(Optional.of(userRole));
        when(userRoleService.save(any())).thenReturn(userRole);
        UserRole actual = userRoleService.update(2, userRoleUpdateDTO);
        assertEquals(2, actual.getId());
        assertEquals(UPDATE_ROLE_NAME, actual.getName());
        assertEquals(UPDATE_ROLE, actual.getCode());
    }

    @Test
    void get() {
        UserRole userRole = new UserRole(1, ROLE_NAME, ROLE_CODE, Collections.emptyList());
        when(userRolesRepository.findById(1)).thenReturn(Optional.of(userRole));
        UserRole actualRole = userRoleService.get(1);
        assertEquals(1, actualRole.getId());
        assertEquals(ROLE_NAME, actualRole.getName());
        assertEquals(ROLE_CODE, actualRole.getCode());
    }

    @Test
    void delete() {
        userRoleService.delete(1);
        // TODO: 07/05/2023    Buni optimalroq qilish kerak
    }

    @Test
    void getRoles() {
        List<UserRole> userRoles = List.of(new UserRole(1, ROLE_NAME, ROLE_CODE, Collections.emptyList()), new UserRole(2, "Manager role", "MANAGER", Collections.emptyList()));
        when(userRolesRepository.findAll()).thenReturn(userRoles);
        List<UserRole> roles = userRoleService.getRoles();
        assertEquals(1, roles.get(0).getId());
        assertEquals(2, roles.get(1).getId());
        assertEquals(ROLE_NAME, roles.get(0).getName());
    }

    @Test
    void addPermission() {
        UserRole userRole = new UserRole(1, ROLE_NAME, ROLE_CODE, new ArrayList<>());
        UserPermission userPermission = new UserPermission(1, "Create user", "CREATE_USER");
        UserRolePermissionDTO userRolePermissionDTO = new UserRolePermissionDTO(1, List.of(1));
        when(userRolesRepository.findById(1)).thenReturn(Optional.of(userRole));
        when(userRolesRepository.save(any())).thenReturn(userRole);
        when(userPermissionService.getPermissionById(1)).thenReturn(userPermission);
        UserRole actualUserRole = userRoleService.addPermission(userRolePermissionDTO);
        assertEquals(1, actualUserRole.getId());
    }

}