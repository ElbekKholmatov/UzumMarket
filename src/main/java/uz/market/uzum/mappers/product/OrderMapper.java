package uz.market.uzum.mappers.product;

import org.mapstruct.Mapper;
import uz.market.uzum.domains.product.Order;
import uz.market.uzum.dtos.order.AddToOrderDTO;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toOrder(AddToOrderDTO addToOrderDTO);

    AddToOrderDTO toAppToOrderDTO(Order order);

}
