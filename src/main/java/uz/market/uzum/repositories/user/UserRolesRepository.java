package uz.market.uzum.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import uz.market.uzum.domains.user.UserRole;


public interface UserRolesRepository extends JpaRepository<UserRole, Integer> {
    @Query("select u from UserRole u where u.code = ?1")
    UserRole findByCode(@NonNull String code);
}