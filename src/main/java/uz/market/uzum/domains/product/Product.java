package uz.market.uzum.domains.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.market.uzum.domains.Auditable;
import uz.market.uzum.domains.Document;
import uz.market.uzum.enums.ProductStatus;

import java.util.Collection;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends Auditable {

    @Id
    private Long id;
    private String name;
    private Integer price;
    private Short discount;
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


}
