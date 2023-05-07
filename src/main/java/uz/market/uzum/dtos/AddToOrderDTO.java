package uz.market.uzum.dtos;


import org.springdoc.core.annotations.ParameterObject;
import uz.market.uzum.domains.product.ProductOrder;
import uz.market.uzum.enums.Payment;

import java.util.Collection;
@ParameterObject
public record AddToOrderDTO(
        Collection<Long> productIds,
        Payment payment
) {
}
