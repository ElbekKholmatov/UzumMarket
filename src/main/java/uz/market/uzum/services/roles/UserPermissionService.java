package uz.market.uzum.services.roles;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.market.uzum.domains.user.UserPermission;
import uz.market.uzum.exceptions.DuplicatePermissionCodeException;
import uz.market.uzum.exceptions.ItemNotFoundException;
import uz.market.uzum.repositories.user.UserPermissionRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserPermissionService {
    private final UserPermissionRepository userPermissionRepository;


    public UserPermission save(UserPermission permission) {
        try {
            return userPermissionRepository.save(permission);
        } catch (Exception e) {
            throw new DuplicatePermissionCodeException("\"%s\" permission code already exists".formatted(permission.getCode()));
        }
    }

    public UserPermission update(UserPermission permission) {
        UserPermission userPermission = userPermissionRepository.findById(permission.getId())
                .orElseThrow(() -> new ItemNotFoundException("Permission with id %d not found".formatted(permission.getId())));
        userPermission.setCode(Objects.requireNonNullElse(permission.getCode(), userPermission.getCode()));
        userPermission.setName(Objects.requireNonNullElse(permission.getName(), userPermission.getName()));
        return userPermissionRepository.save(userPermission);
    }

    public UserPermission getPermissionById(Integer id) {
        return userPermissionRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("No permission found with id %s".formatted(id)));
    }

    public void delete(Integer id) {
        userPermissionRepository.deleteById(id);
    }

    public List<UserPermission> getAll() {
        return userPermissionRepository.findAll();
    }
}
