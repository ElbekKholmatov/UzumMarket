package uz.market.uzum.mappers.user;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import uz.market.uzum.domains.user.User;
import uz.market.uzum.dtos.auth.UserCreateDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-05T21:29:40+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Private Build)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserCreateDTO toDto(User user) {
        if ( user == null ) {
            return null;
        }

        String password = null;
        String firstName = null;
        String lastName = null;
        String phoneNumber = null;
        String email = null;

        password = user.getPassword();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        phoneNumber = user.getPhoneNumber();
        email = user.getEmail();

        UserCreateDTO userCreateDTO = new UserCreateDTO( password, firstName, lastName, phoneNumber, email );

        return userCreateDTO;
    }

    @Override
    public User toEntity(UserCreateDTO userDto) {
        if ( userDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.email( userDto.email() );
        user.password( userDto.password() );
        user.firstName( userDto.firstName() );
        user.lastName( userDto.lastName() );
        user.phoneNumber( userDto.phoneNumber() );

        return user.build();
    }
}
