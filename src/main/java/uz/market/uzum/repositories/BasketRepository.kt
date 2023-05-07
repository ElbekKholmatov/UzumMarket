package uz.market.uzum.repositories;

import org.springframework.data.jpa.repository.JpaRepository
import uz.market.uzum.domains.product.Basket

interface BasketRepository : JpaRepository<Basket, Long> {
}