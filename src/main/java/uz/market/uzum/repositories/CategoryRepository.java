package uz.market.uzum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.market.uzum.domains.product.Category;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("select c from Category c where c.isDeleted = false and c.id = ?1")
    Optional<Category> getCategoryById(Integer integer);

    @Query("select c from Category c where c.isDeleted = false")
    List<Category> getAll();


}
