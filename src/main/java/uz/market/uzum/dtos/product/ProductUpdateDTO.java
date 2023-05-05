package uz.market.uzum.dtos.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.stereotype.Component;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ParameterObject
public class ProductUpdateDTO {

    @NotNull(message = "ProductID cannot be null")
    private Integer productID;

    @NotNull(message = "CategoryID cannot be null")
    private Integer categoryID;

    @Size(min = 3, max = 50, message = "Product name must be between {min} and {max} character")
    private String name;

    @Size(min = 0, max = 200, message = "Restaurant description must be between {min} and {max}")
    private String description;

    private Double price;

    private Double discount;
}
