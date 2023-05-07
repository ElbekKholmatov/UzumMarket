package uz.market.uzum.domains.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.market.uzum.domains.Auditable;
import uz.market.uzum.enums.ProductOrderStatus;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductOrder extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Product product;

    private Short count;

    private Long basketId;
}