package uz.market.uzum.dtos.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.stereotype.Component;
import uz.market.uzum.enums.ProductStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ParameterObject
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductCreateDTO {

    @Size(min = 3, max = 50, message = "Product name must be between {min} and {max} character")
    private String name;

    @Size(max = 200, message = "Product description must be less than {max} character")
    private String description;
    @NotNull
    private Double price;
    @NotNull
    private Integer count;
    @NotNull
    private Double discount;

    @NotNull(message = "Category id cannot be null")
    private Integer categoryID;

    @NotNull
    private ProductStatus status;

}
