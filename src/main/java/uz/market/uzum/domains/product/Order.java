package uz.market.uzum.domains.product;


import jakarta.persistence.*;
import lombok.*;
import uz.market.uzum.domains.Auditable;
import uz.market.uzum.enums.OrderStatus;
import uz.market.uzum.enums.Payment;

import java.time.LocalDateTime;
import java.util.Collection;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
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

    @Builder(builderMethodName = "childBuilder")
    public Order(Long createdBy, Long updateBy, LocalDateTime createdAt, LocalDateTime updatedAt, boolean isDeleted, Long id, Long userId, Collection<ProductOrder> products, OrderStatus status, Payment payment) {
        super(createdBy, updateBy, createdAt, updatedAt, isDeleted);
        this.id = id;
        this.userId = userId;
        this.products = products;
        this.status = status;
        this.payment = payment;
    }
}
