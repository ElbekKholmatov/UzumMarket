package uz.market.uzum.dtos.roles;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springdoc.core.annotations.ParameterObject;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ParameterObject
public class UserRolePermissionDTO {
    @NonNull
    @JsonProperty("user_role_id")
    private Integer userRoleId;

    @NonNull
    @JsonProperty("user_permission_id")
    private List<Integer> userPermissionId;
}
