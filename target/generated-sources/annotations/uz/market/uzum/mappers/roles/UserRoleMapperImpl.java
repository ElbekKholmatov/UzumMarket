package uz.market.uzum.mappers.roles;

import javax.annotation.processing.Generated;
import uz.market.uzum.domains.user.UserRole;
import uz.market.uzum.dtos.roles.UserRoleCreateDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-06T09:25:31+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
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
