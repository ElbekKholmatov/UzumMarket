package uz.market.uzum.services.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.market.uzum.domains.user.User;
import uz.market.uzum.exceptions.UserNotFoundException;
import uz.market.uzum.repositories.user.UserRepository;

import java.util.Collection;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByPhoneNumber(username);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));
    }

    public Collection<User> findAll() {
        return userRepository.findAll();
    }
}
