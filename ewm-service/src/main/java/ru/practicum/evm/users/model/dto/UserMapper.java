package ru.practicum.evm.users.model.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.practicum.evm.users.model.NewUserRequest;
import ru.practicum.evm.users.model.User;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User newUserRequestToUser(NewUserRequest newUserRequest);

    UserDto userToUserDto(User user);
}
