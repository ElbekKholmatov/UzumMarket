package uz.market.uzum.domains.product;


import jakarta.persistence.*;
import lombok.*;
import uz.market.uzum.domains.Auditable;
import uz.market.uzum.domains.user.User;

import java.time.LocalDateTime;
import java.util.Collection;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Basket extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Collection<ProductOrder> product;

    @OneToOne
    private User user;

    @Builder(builderMethodName = "basketBuilder")
    public Basket(Long createdBy, Long updateBy,
                  LocalDateTime createdAt, LocalDateTime updatedAt,
                  boolean isDeleted, Long id, Collection<ProductOrder> product, User user) {
        super(createdBy, updateBy, createdAt, updatedAt, isDeleted);
        this.id = id;
        this.product = product;
        this.user = user;
    }

    public Basket(Collection<ProductOrder> product, User user) {
        this.product = product;
        this.user = user;
    }
}
