package uz.market.uzum.domains.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import uz.market.uzum.domains.Auditable;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment extends Auditable {

    @Id
    private Long id;
    private Byte rate;  // rating
    private String text;
    private Long productId;


    @Builder(builderMethodName = "commentBuilder")
    public Comment(Long createdBy, Long updateBy, LocalDateTime createdAt, LocalDateTime updatedAt, boolean isDeleted, Long id, String text, Long productId) {
        super(createdBy, updateBy, createdAt, updatedAt, isDeleted);
        this.id = id;
        this.text = text;
        this.productId = productId;
    }
}
