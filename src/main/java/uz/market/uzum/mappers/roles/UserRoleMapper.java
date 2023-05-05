package uz.market.uzum.mappers.roles;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uz.market.uzum.domains.user.UserRole;
import uz.market.uzum.dtos.roles.UserRoleCreateDTO;

@Mapper
public interface UserRoleMapper {
    UserRoleMapper ROLE_MAPPER = Mappers.getMapper(UserRoleMapper.class);

    UserRole fromUserCreateDTOtoUserRole(UserRoleCreateDTO dto);
}
