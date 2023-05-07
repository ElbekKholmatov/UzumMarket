package uz.market.uzum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.market.uzum.domains.product.Product;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select p from Product p where p.isDeleted = false and p.id = ?1")
    Optional<Product> getProductById(Integer id);

    @Query("select p from Product p where p.isDeleted = false and p.category.id = ?1")
    List<Product> findByCategoryId(Integer categoryID);


    @Transactional
    @Modifying
    @Query("update Product p set p.isDeleted = true where p.isDeleted = false and p.id = ?1")
    int updateIsDeletedById(Integer id);
}
