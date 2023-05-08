package uz.market.uzum.domains.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.Where;
import uz.market.uzum.domains.Auditable;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Where(clause = "is_deleted = false")
public class Comment extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Byte rate;
    private String text;
    private Integer productId;

    @Builder(builderMethodName = "commentBuilder")
    public Comment(Long createdBy, Long updateBy, LocalDateTime createdAt, LocalDateTime updatedAt, boolean isDeleted, Integer id, String text, Byte rate, Integer productId) {
        super(createdBy, updateBy, createdAt, updatedAt, isDeleted);
        this.id = id;
        this.text = text;
        this.productId = productId;
        this.rate = rate;
    }
}
