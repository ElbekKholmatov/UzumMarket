package uz.market.uzum.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.market.uzum.domains.user.User;
import uz.market.uzum.repositories.user.UserRepository;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SessionUser {
    private final UserRepository userRepository;

    public User user() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Object principal = authentication.getPrincipal();
        if (!Objects.isNull(principal))
            return userRepository.findByPhoneNumber(principal.toString());
        return null;
    }

    public Long id() {
        User user = user();
        if (Objects.isNull(user))
            return -1L;
        return user.getId();
    }
}
