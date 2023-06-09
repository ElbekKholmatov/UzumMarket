package uz.market.uzum.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.market.uzum.domains.product.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.status = 'NEW'")
    Page<Order> findAllByStatus(Pageable pageable);

    @Query("select o from Order o where o.status = 'PAYMENT_PERIOD'")
    Page<Order> findAllOnPaying(Pageable pageable);
}