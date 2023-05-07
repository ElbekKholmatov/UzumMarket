package uz.market.uzum.repositories.product;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.market.uzum.domains.product.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
}