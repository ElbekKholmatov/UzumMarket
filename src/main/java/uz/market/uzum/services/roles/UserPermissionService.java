package uz.market.uzum.services.roles;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import uz.market.uzum.domains.user.UserPermission;
import uz.market.uzum.dtos.roles.UserPermissionCreateDTO;
import uz.market.uzum.exceptions.DuplicatePermissionCodeException;
import uz.market.uzum.exceptions.ItemNotFoundException;
import uz.market.uzum.repositories.user.UserPermissionRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "userPermission")
public class UserPermissionService {
    private final UserPermissionRepository userPermissionRepository;


    @Cacheable(key = "#root.methodName")
    public UserPermission save(UserPermissionCreateDTO createDTO) {
        try {
            UserPermission permission = UserPermission.builder()
                    .name(createDTO.getName())
                    .code(createDTO.getCode())
                    .build();
            return userPermissionRepository.save(permission);
        } catch (Exception e) {

            throw new DuplicatePermissionCodeException("\"%s\" permission code already exists".formatted(createDTO.getCode()));
        }
    }

    @CachePut(key = "#permission.id")
    public UserPermission update(UserPermission permission) {
        UserPermission userPermission = userPermissionRepository.findById(permission.getId())
                .orElseThrow(() -> new ItemNotFoundException("Permission with id %d not found".formatted(permission.getId())));
        userPermission.setCode(Objects.requireNonNullElse(permission.getCode(), userPermission.getCode()));
        userPermission.setName(Objects.requireNonNullElse(permission.getName(), userPermission.getName()));
        return userPermissionRepository.save(userPermission);
    }

    @Cacheable(key = "#id")
    public UserPermission getPermissionById(Integer id) {
        return userPermissionRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("No permission found with id %s".formatted(id)));
    }

    @CacheEvict(key = "#id")
    public void delete(Integer id) {
        userPermissionRepository.deleteById(id);
    }

    @Cacheable(key = "#root.methodName")
    public List<UserPermission> getAll() {
        return userPermissionRepository.findAll();
    }
}
