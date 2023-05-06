package uz.market.uzum.dtos.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springdoc.core.annotations.ParameterObject;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ParameterObject
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryCreateDTO {
    @NotNull(message = "Category name must not be blank")
    @Size(min = 3, max = 50, message = "Category name must be between {min} and {max}")
    private String name;

    private Integer parentID;

}
