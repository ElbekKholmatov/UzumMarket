package uz.market.uzum.mappers.roles;

import javax.annotation.processing.Generated;
import uz.market.uzum.domains.user.UserRole;
import uz.market.uzum.dtos.roles.UserRoleCreateDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-05T15:23:53+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class UserRoleMapperImpl implements UserRoleMapper {

    @Override
    public UserRole fromUserCreateDTOtoUserRole(UserRoleCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        UserRole.UserRoleBuilder userRole = UserRole.builder();

        userRole.name( dto.getName() );
        userRole.code( dto.getCode() );

        return userRole.build();
    }
}
