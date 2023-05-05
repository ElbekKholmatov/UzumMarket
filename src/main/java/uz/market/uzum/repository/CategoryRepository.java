package uz.market.uzum.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.market.uzum.domains.product.Category;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Transactional
    @Modifying
    @Query("update Category c set c.deleted = true where c.id = ?2 and c.deleted = false")
    int updateIsDeletedById(Integer id);


    @Override
    @Query("select c from Category c where c.deleted = false and c.id = ?1")
    @NonNull Optional<Category> findById(@NonNull Integer integer);

    @Query("select c from Category c where c.deleted = false")
    List<Category> findAll();


}
