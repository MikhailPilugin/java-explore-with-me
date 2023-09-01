package ru.practicum.evm.users.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.practicum.evm.exceptions.ApiError;
import ru.practicum.evm.exceptions.IncorrectRequestEcxeption;
import ru.practicum.evm.exceptions.NotFoundException;
import ru.practicum.evm.users.model.dto.UserDto;
import ru.practicum.evm.users.model.dto.UserMapper;
import ru.practicum.evm.users.model.NewUserRequest;
import ru.practicum.evm.users.model.User;
import ru.practicum.evm.users.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDto> getUsers(List<Long> ids, Pageable pageable) {
        List<UserDto> userDtos;
        if (ids != null) {
            userDtos = userRepository.getAllByIdIn(ids, pageable).stream()
                    .map(UserMapper.INSTANCE::userToUserDto)
                    .collect(Collectors.toList());
        } else {
            userDtos = userRepository.findAll(pageable).stream()
                    .map(UserMapper.INSTANCE::userToUserDto)
                    .collect(Collectors.toList());
        }
        return userDtos;
    }

    @Override
    public UserDto addUser(NewUserRequest newUserRequest) {
        if (userRepository.getUserByName(newUserRequest.getName()) != null) {
            throw new IncorrectRequestEcxeption(new ApiError(HttpStatus.CONFLICT.toString(),
                    "Пользователь с таким именем уже существует",
                    "Пользователь с таким именем уже существует", LocalDateTime.now().toString()));
        }
        User user = userRepository.save(UserMapper.INSTANCE.newUserRequestToUser(newUserRequest));
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.findById(userId).orElseThrow(NotFoundException::new);
        userRepository.deleteById(userId);
    }
}
