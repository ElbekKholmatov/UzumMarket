package uz.market.uzum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.market.uzum.domains.product.Basket;

import java.util.Optional;


@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    Optional<Basket> findByUserId(Long userId);
}
