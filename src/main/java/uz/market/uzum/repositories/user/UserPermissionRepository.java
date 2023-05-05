package uz.market.uzum.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.market.uzum.domains.user.UserPermission;

public interface UserPermissionRepository extends JpaRepository<UserPermission, Integer> {
}