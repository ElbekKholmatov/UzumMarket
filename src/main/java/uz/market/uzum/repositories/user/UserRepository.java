package uz.market.uzum.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.market.uzum.domains.user.User;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where upper(u.email) = upper(?1)")
    Optional<User> checkUniqueFields(String email);

    @Query("select u from User u where u.email = ?1")
    User findByEmail(String username);


    Optional<User> findByEmailLike(String email);

    @Query("select u from User u")
    Optional<Collection<User>> findAllUserDetails();


}