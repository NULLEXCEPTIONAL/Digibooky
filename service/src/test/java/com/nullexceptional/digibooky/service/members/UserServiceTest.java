package com.nullexceptional.digibooky.service.members;

import com.nullexceptional.digibooky.domain.members.User;
import com.nullexceptional.digibooky.domain.members.UserRepository;
import com.nullexceptional.digibooky.domain.members.dto.UserDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Test
    void getAllUsersMethod() {
        UserRepository userRepository = new UserRepository();
        UserMapper userMapper = new UserMapper();
        UserService userService = new UserService(userRepository,userMapper);
        UserDto userDto = userService.getAllUsers().stream().filter(user -> user.getEmail().equals("poppy@gmail.com")).findFirst().orElse(null);
        assertThat("Poppy").isEqualTo(userDto.getFirstName());
        assertThat(4).isEqualTo(userService.getAllUsers().size());
    }

    @Test
    void addUserMethod() {
        UserRepository userRepository = new UserRepository();
        UserMapper userMapper = new UserMapper();
        UserService userService = new UserService(userRepository,userMapper);
        UserDto userDto = new UserDto(UUID.randomUUID(), "123456", "Akagami", "Omar", "omar@hotmail.com", null);
        userService.addUser(userDto);
        User actualUserDto = userRepository.getUserByInss("123456");
        assertThat(actualUserDto.getLastName()).isEqualTo("Omar");
    }

    @Test
    void getUserByIdMethod() {
        UserRepository userRepository = new UserRepository();
        UserMapper userMapper = new UserMapper();
        UserService userService = new UserService(userRepository,userMapper);
        UserDto userDto = new UserDto(UUID.randomUUID(), "123456", "Akagami", "Omar", "omar@hotmail.com", null);
        userService.addUser(userDto);
        User actualUserDto = userRepository.getUserById(userDto.getId());
        assertThat(actualUserDto.getLastName()).isEqualTo("Omar");
    }

}