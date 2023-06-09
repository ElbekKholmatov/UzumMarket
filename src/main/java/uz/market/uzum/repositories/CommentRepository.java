package uz.market.uzum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.market.uzum.domains.product.Comment;

import java.util.List;
import java.util.Optional;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query(nativeQuery = true, value = "select * from comment   where  product_id=?1 and is_deleted=false ")
    Optional<List<Comment>> findByProductId(Integer productId);


}
