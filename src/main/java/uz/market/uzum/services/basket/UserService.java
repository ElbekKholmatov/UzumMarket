package uz.market.uzum.services.basket;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.market.uzum.domains.user.User;
import uz.market.uzum.exceptions.UserNotFoundException;
import uz.market.uzum.repositories.UserRepository;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
//    private final UserRolesRepository userRolesRepository;


    public User save(User user) {
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null/*userRepository.findByPhoneNumber(username)*/;
    }

    /*public User addRoles(@NonNull UserRolesCreateDTO roleIds) {
        User user = userRepository.findById(roleIds.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Collection<UserRole> roles = user.getRoles();
        Collection<UserRole> newRoles = roleIds.getRoleIds().stream().map(roleId -> {
            return userRolesRepository.findById(roleId).orElseThrow(() -> new ItemNotFoundException("Role not found"));
        }).toList();
        roles.addAll(newRoles);
        return userRepository.save(user);

    }*/

    /*public User removeRoles(UserRolesCreateDTO dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Collection<UserRole> roles = user.getRoles();
        Collection<UserRole> newRoles = dto.getRoleIds().stream().map(roleId -> {
            return userRolesRepository.findById(roleId).orElseThrow(() -> new ItemNotFoundException("Role not found"));
        }).toList();
        roles.removeAll(newRoles);
        return userRepository.save(user);
    }*/
}
