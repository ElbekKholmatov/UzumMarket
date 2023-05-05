package uz.market.uzum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.market.uzum.domains.product.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}