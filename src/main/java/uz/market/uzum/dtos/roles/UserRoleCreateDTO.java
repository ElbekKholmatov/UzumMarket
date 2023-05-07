package uz.market.uzum.dtos.roles;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springdoc.core.annotations.ParameterObject;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ParameterObject
public class UserRoleCreateDTO {
    @NotBlank(message = "Name must not be blank")
    private String name;
    @NotBlank(message = "Code must not be blank")
    @Pattern(regexp = "^[A-Z_]+$", message = "Code must be in uppercase and underscore")
    private String code;

}