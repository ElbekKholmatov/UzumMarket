package uz.market.uzum.domains.product;

import jakarta.persistence.*;
import lombok.*;
import uz.market.uzum.domains.Auditable;
import uz.market.uzum.domains.Document;
import uz.market.uzum.enums.ProductStatus;

import java.time.LocalDateTime;
import java.util.Collection;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double price;
    private Double discount;
    private Integer count;
    private Integer ordersCount;
    private Long likesCount;
    private Long viewsCount;
    private Byte rating;
    private String description;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Category category;

    @OneToMany
    private Collection<Document> images;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Collection<Comment> comments;

    @Builder(builderMethodName = "childBuilder")
    public Product(Long createdBy, Long updateBy, LocalDateTime createdAt, LocalDateTime updatedAt, boolean isDeleted, Integer id, String name, Double price, Double discount, Integer count, Integer ordersCount, Long likesCount, Long viewsCount, Byte rating, String description, Category category, Collection<Document> images, ProductStatus status, Collection<Comment> comments) {
        super(createdBy, updateBy, createdAt, updatedAt, isDeleted);
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.count = count;
        this.ordersCount = ordersCount;
        this.likesCount = likesCount;
        this.viewsCount = viewsCount;
        this.rating = rating;
        this.description = description;
        this.category = category;
        this.images = images;
        this.status = status;
        this.comments = comments;
    }

}
