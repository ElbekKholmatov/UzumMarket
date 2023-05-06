package uz.market.uzum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.market.uzum.domains.product.ProductOrder;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
}