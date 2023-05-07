package uz.market.uzum.mappers.product;

import java.util.Collection;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import uz.market.uzum.domains.product.Order;
import uz.market.uzum.dtos.order.AddToOrderDTO;
import uz.market.uzum.enums.Payment;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-07T09:37:53+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order toOrder(AddToOrderDTO addToOrderDTO) {
        if ( addToOrderDTO == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.childBuilder();

        order.payment( addToOrderDTO.payment() );

        return order.build();
    }

    @Override
    public AddToOrderDTO toAppToOrderDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        Payment payment = null;

        payment = order.getPayment();

        Collection<Long> productIds = null;

        AddToOrderDTO addToOrderDTO = new AddToOrderDTO( productIds, payment );

        return addToOrderDTO;
    }
}
