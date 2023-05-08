package uz.market.uzum.services.roles;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uz.market.uzum.domains.user.UserPermission;
import uz.market.uzum.dtos.roles.UserPermissionCreateDTO;
import uz.market.uzum.exceptions.ItemNotFoundException;
import uz.market.uzum.repositories.user.UserPermissionRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ContextConfiguration(classes = {UserPermissionService.class})
@ExtendWith(SpringExtension.class)
class UserPermissionServiceTest {


    private static final String CODE_CREATE_CATEGORY = "CREATE_CATEGORY";
    private static final String CODE_UPDATE_CATEGORY = "UPDATE_CATEGORY";
    private static final String NAME_CREATE_CATEGORY = "Create category";
    private static final String UPDATE_CATEGORY = "Update category";
    @Autowired
    private UserPermissionService userPermissionService;

    @MockBean
    private UserPermissionRepository userPermissionRepository;


    @Test
    void save() {
        UserPermission userPermission = UserPermission.builder().id(1).name(NAME_CREATE_CATEGORY).code(CODE_CREATE_CATEGORY).build();
        when(userPermissionRepository.save(any())).thenReturn(userPermission);
        UserPermission actual = userPermissionService.save(new UserPermissionCreateDTO());
        assertEquals(1, actual.getId());
        assertEquals(NAME_CREATE_CATEGORY, actual.getName());
        assertEquals(CODE_CREATE_CATEGORY, actual.getCode());
    }

    @Test
    void update() {
        UserPermission userPermission = new UserPermission(2, NAME_CREATE_CATEGORY, CODE_CREATE_CATEGORY);
        UserPermission updateUserPermission = new UserPermission(2, UPDATE_CATEGORY, "UPDATE_CATEGORY");

        when(userPermissionRepository.findById(2)).thenReturn(Optional.of(userPermission));
        when(userPermissionRepository.save(any())).thenReturn(userPermission);
        UserPermission actual = userPermissionService.update(updateUserPermission);
        assertEquals(2, actual.getId());
        assertEquals(UPDATE_CATEGORY, actual.getName());
        assertEquals("UPDATE_CATEGORY", actual.getCode());
    }

    @Test
    void getPermissionById() {
        UserPermission userPermission = new UserPermission(2, NAME_CREATE_CATEGORY, CODE_CREATE_CATEGORY);

        when(userPermissionRepository.findById(2)).thenReturn(Optional.of(userPermission));

        UserPermission actualPermission = userPermissionService.getPermissionById(2);
        assertEquals(2, actualPermission.getId());
    }

    @Test
    void getPermissionByIdIfNotFoundShouldThrowException() {
        UserPermission userPermission = new UserPermission(2, NAME_CREATE_CATEGORY, CODE_CREATE_CATEGORY);

        when(userPermissionRepository.findById(2)).thenReturn(Optional.of(userPermission));
        assertThrows(ItemNotFoundException.class, () -> userPermissionService.getPermissionById(1)).printStackTrace();
    }

    @Test
    void delete() {
        userPermissionService.delete(1);
    }

    @Test
    void getAll() {
        List<UserPermission> userPermissionList = List.of(
                new UserPermission(2, NAME_CREATE_CATEGORY, CODE_CREATE_CATEGORY),
                new UserPermission(3, UPDATE_CATEGORY, CODE_UPDATE_CATEGORY)
        );
        when(userPermissionRepository.findAll()).thenReturn(userPermissionList);
        List<UserPermission> actualUserPermissionList = userPermissionService.getAll();
        assertEquals(2, actualUserPermissionList.size());
        assertEquals(NAME_CREATE_CATEGORY, actualUserPermissionList.get(0).getName());
        assertEquals(CODE_CREATE_CATEGORY, actualUserPermissionList.get(0).getCode());

    }
}