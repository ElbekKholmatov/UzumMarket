package uz.market.uzum.mappers.product;

import java.util.Collection;
import javax.annotation.processing.Generated;
import uz.market.uzum.domains.product.Order;
import uz.market.uzum.domains.product.ProductOrder;
import uz.market.uzum.dtos.order.AddToOrderDTO;
import uz.market.uzum.enums.Payment;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-06T08:23:19+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Private Build)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order toOrder(AddToOrderDTO addToOrderDTO) {
        if ( addToOrderDTO == null ) {
            return null;
        }

        Order order = new Order();

        order.setUserId( addToOrderDTO.userId() );
        order.setPayment( addToOrderDTO.payment() );

        return order;
    }

    @Override
    public AddToOrderDTO toAppToOrderDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        Long userId = null;
        Payment payment = null;

        userId = order.getUserId();
        payment = order.getPayment();

        Collection<ProductOrder> productIds = null;

        AddToOrderDTO addToOrderDTO = new AddToOrderDTO( productIds, userId, payment );

        return addToOrderDTO;
    }
}
