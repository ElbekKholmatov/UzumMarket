package uz.market.uzum.dtos;

import org.springdoc.core.annotations.ParameterObject;
import uz.market.uzum.enums.OrderStatus;

@ParameterObject
public record ChangeOrderStatusDTO(
        Long orderId,
        OrderStatus status
) {
}
