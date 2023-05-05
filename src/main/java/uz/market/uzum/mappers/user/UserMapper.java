package uz.market.uzum.mappers.user;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uz.market.uzum.domains.user.User;
import uz.market.uzum.dtos.auth.UserCreateDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserCreateDTO toDto(User user);

    User toEntity(UserCreateDTO userDto);
}
