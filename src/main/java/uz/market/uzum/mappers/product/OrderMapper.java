package uz.market.uzum.mappers.product;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import uz.market.uzum.domains.product.Order;
import uz.market.uzum.dtos.order.AddToOrderDTO;
import uz.market.uzum.mappers.user.UserMapper;

@Component
@Mapper
public interface OrderMapper {
 OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
 Order toOrder(AddToOrderDTO addToOrderDTO);
 AddToOrderDTO toAppToOrderDTO(Order order);

}
