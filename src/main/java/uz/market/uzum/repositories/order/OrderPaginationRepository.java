package uz.market.uzum.repositories.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import uz.market.uzum.domains.product.Order;
@Repository
public interface OrderPaginationRepository extends PagingAndSortingRepository<Order, Long> {

}
