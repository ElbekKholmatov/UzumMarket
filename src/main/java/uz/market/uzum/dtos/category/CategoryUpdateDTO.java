package uz.market.uzum.dtos.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springdoc.core.annotations.ParameterObject;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ParameterObject
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryUpdateDTO {

    @NotNull(message = "Category Id cannot be null")
    private Integer categoryID;

    @Size(min = 3, max = 50, message = "Category name must be between {min} and {max} character")
    private String name;

    private Integer parentID;
}
