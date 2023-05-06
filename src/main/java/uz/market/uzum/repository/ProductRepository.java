//package uz.market.uzum.repository;
//
//import lombok.NonNull;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.transaction.annotation.Transactional;
//import uz.market.uzum.domains.product.Product;
//
//import java.util.List;
//import java.util.Optional;
//
//
//public interface ProductRepository extends JpaRepository<Product, Integer> {
//
//    @Query("select p from Product p where p.deleted = false and p.id = ?1 and p.category.id = ?2")
//    @NonNull Optional<Product> findById(@NonNull Integer id);
//
//    List<Product> findByCategoryId(Integer categoryID);
//
//
//    @Transactional
//    @Modifying
//    @Query("update Product p set p.deleted = true where p.deleted = false and p.id = ?1")
//    int updateIsDeletedById(Integer id);
//}
