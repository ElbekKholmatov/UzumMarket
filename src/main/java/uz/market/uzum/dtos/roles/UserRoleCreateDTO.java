package uz.market.uzum.dtos.roles;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRoleCreateDTO {
    @NotBlank(message = "Name must not be blank")
    private String name;
    @NotBlank(message = "Code must not be blank")
    @Pattern(regexp = "^[A-Z_]+$", message = "Code must be in uppercase and underscore")
    private String code;

}