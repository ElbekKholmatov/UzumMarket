package uz.market.uzum.dtos.category;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryUpdateDTO {

    @NotNull(message = "Category Id cannot be null")
    private Integer categoryID;

    @Size(min = 3, max = 35, message = "Category name size must be 3-35 character")
    private String name;

    private Integer parentID;
}
