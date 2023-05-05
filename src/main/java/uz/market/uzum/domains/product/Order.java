package uz.market.uzum.domains.product;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.market.uzum.domains.Auditable;
import uz.market.uzum.enums.OrderStatus;
import uz.market.uzum.enums.Payment;

import java.util.Collection;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order extends Auditable {
    @Id
    private Long id;

    private Long userId;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<ProductOrder> products;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @Enumerated(EnumType.STRING)
    private Payment payment;
}
