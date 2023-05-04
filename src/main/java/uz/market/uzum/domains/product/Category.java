package uz.market.uzum.domains.product;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import uz.market.uzum.domains.Auditable;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category extends Auditable {

    @Id
    private Short id;

    @ManyToOne
    private Category parent;

    private String name;

    @Builder(builderMethodName = "childBuilder")
    public Category(Long createdBy, Long updateBy, LocalDateTime createdAt, LocalDateTime updatedAt,
                    boolean isDeleted, Short id, Category parent, String name) {
        super(createdBy, updateBy, createdAt, updatedAt, isDeleted);
        this.id = id;
        this.parent = parent;
        this.name = name;
    }
}
