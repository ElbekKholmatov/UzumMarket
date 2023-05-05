package uz.market.uzum.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.market.uzum.domains.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByPhoneNumber(String username);

    Optional<User> findByPhoneNumberLike(String phone);
}