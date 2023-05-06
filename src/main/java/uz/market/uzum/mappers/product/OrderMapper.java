package uz.market.uzum.mappers.product;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.market.uzum.domains.product.Order;
import uz.market.uzum.dtos.order.AddToOrderDTO;

@Mapper()
public interface OrderMapper {
 Order toOrder(AddToOrderDTO addToOrderDTO);
 AddToOrderDTO toAppToOrderDTO(Order order);

}
