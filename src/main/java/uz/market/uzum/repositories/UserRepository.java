package uz.market.uzum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.market.uzum.domains.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
