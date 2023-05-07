package uz.market.uzum.services.roles;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.market.uzum.domains.user.UserPermission;
import uz.market.uzum.domains.user.UserRole;
import uz.market.uzum.dtos.roles.UserRoleCreateDTO;
import uz.market.uzum.dtos.roles.UserRolePermissionDTO;
import uz.market.uzum.exceptions.DuplicatePermissionForSingleRoleException;
import uz.market.uzum.exceptions.DuplicateUserRoleCodeException;
import uz.market.uzum.exceptions.ItemNotFoundException;
import uz.market.uzum.mappers.roles.UserRoleMapper;
import uz.market.uzum.repositories.user.UserRolesRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final UserRolesRepository userRolesRepository;
    private final UserPermissionService userPermissionService;

    public UserRole save(UserRoleCreateDTO dto) {
        try {
            UserRole role = UserRoleMapper.ROLE_MAPPER.fromUserCreateDTOtoUserRole(dto);
            return userRolesRepository.save(role);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DuplicateUserRoleCodeException("User role with \"%s\" code already exists".formatted(dto.getCode()));
        }
    }

    public UserRole update(Integer id, UserRoleCreateDTO dto) {
        UserRole userRole = userRolesRepository.findById(id).orElseThrow(() ->
                new ItemNotFoundException("Role with id: " + id + " not found"));
        userRole.setName(Objects.requireNonNullElse(dto.getName(), userRole.getName()));
        userRole.setCode(Objects.requireNonNullElse(dto.getCode(), userRole.getCode()));
        userRole.setId(id);
        /*if (Objects.isNull(userRole.getAuthPermissions())){
            userRole.setAuthPermissions(new ArrayList<>());
        }*/
        try {
            return userRolesRepository.save(userRole);
        } catch (Exception e) {
            throw new DuplicateUserRoleCodeException("User role with %s code already exists".formatted(dto.getCode()));
        }
    }

    public UserRole get(Integer id) {
        return userRolesRepository.findById(id).orElseThrow(() ->
                new ItemNotFoundException("Role with id: " + id + " not found"));

    }

    public void delete(Integer id) {
        userRolesRepository.deleteById(id);
    }

    public List<UserRole> getRoles() {
        return userRolesRepository.findAll();
    }

    public UserRole addPermission(UserRolePermissionDTO dto) {
        UserRole role = get(dto.getUserRoleId());
        Collection<UserPermission> authPermissions = Objects
                .requireNonNullElse(role.getAuthPermissions(),
                        new ArrayList<>());
        for (Integer integer : dto.getUserPermissionId()) {
            UserPermission permission = userPermissionService.getPermissionById(integer);
            authPermissions.add(permission);
        }
        role.setAuthPermissions(authPermissions);
        try {
            return userRolesRepository.save(role);
        } catch (Exception e) {
            throw new DuplicatePermissionForSingleRoleException("Permission already exists for the role %s ".formatted(role.getName()));
        }
    }

    public UserRole removePermission(UserRolePermissionDTO dto) {

        UserRole role = get(dto.getUserRoleId());
        Collection<UserPermission> authPermissions = Objects
                .requireNonNullElse(role.getAuthPermissions(),
                        new ArrayList<>());
        for (Integer integer : dto.getUserPermissionId()) {
            UserPermission permission = userPermissionService.getPermissionById(integer);
            authPermissions.remove(permission);
        }
        role.setAuthPermissions(authPermissions);
        return userRolesRepository.save(role);
    }
}
