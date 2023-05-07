package uz.market.uzum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.market.uzum.domains.product.Basket;


@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
}
