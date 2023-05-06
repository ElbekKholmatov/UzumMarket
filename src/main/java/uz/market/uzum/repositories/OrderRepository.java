package uz.market.uzum.repositories;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.market.uzum.domains.product.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order saveOrder(@NotNull Order order);
}