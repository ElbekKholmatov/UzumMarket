package uz.market.uzum.domains.product;

import jakarta.persistence.*;
import lombok.*;
import uz.market.uzum.domains.Auditable;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder(builderMethodName = "productOrderBuilder")
public class ProductOrder extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Product product;

    private Short count;

    @Column(nullable = false)
    private Long basketId;
}