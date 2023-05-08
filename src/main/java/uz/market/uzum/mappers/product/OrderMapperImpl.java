//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package uz.market.uzum.mappers.product;

import java.util.Collection;
import org.springframework.stereotype.Component;
import uz.market.uzum.domains.product.Order;
import uz.market.uzum.dtos.order.AddToOrderDTO;
import uz.market.uzum.enums.Payment;

@Component
public class OrderMapperImpl implements OrderMapper {
    public OrderMapperImpl() {
    }

    public Order toOrder(AddToOrderDTO addToOrderDTO) {
        if (addToOrderDTO == null) {
            return null;
        } else {
            Order.OrderBuilder order = Order.childBuilder();
            order.payment(addToOrderDTO.payment());
            return order.build();
        }
    }

    public AddToOrderDTO toAppToOrderDTO(Order order) {
        if (order == null) {
            return null;
        } else {
            Payment payment;
            payment = order.getPayment();
            Collection<Long> productIds = null;
            return new AddToOrderDTO(productIds, payment);
        }
    }
}
