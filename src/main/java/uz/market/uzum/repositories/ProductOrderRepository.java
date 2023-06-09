package uz.market.uzum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.market.uzum.domains.product.ProductOrder;

import java.util.Collection;


@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
    @Query("select po from ProductOrder po where po.id in :longs")
    Collection<ProductOrder> findALLByIds(Collection<Long> longs);
}