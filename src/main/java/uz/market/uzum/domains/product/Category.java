package uz.market.uzum.domains.product;


import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Category parent;

    private String name;

    @Builder(builderMethodName = "childBuilder")
    public Category(Long createdBy, Long updateBy, LocalDateTime createdAt, LocalDateTime updatedAt,
                    boolean isDeleted, Integer id, Category parent, String name) {
        super(createdBy, updateBy, createdAt, updatedAt, isDeleted);
        this.id = id;
        this.parent = parent;
        this.name = name;
    }
}
