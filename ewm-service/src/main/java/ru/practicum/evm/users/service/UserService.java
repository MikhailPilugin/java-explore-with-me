package ru.practicum.evm.users.service;

import org.springframework.data.domain.Pageable;
import ru.practicum.evm.users.model.dto.UserDto;
import ru.practicum.evm.users.model.NewUserRequest;

import java.util.List;

public interface UserService {

    List<UserDto> getUsers(List<Long> ids, Pageable pageable);

    UserDto addUser(NewUserRequest userDto);

    void deleteUser(long userId);

}