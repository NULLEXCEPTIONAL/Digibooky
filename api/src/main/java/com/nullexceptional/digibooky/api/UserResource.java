package com.nullexceptional.digibooky.api;

import com.nullexceptional.digibooky.domain.members.User;
import com.nullexceptional.digibooky.domain.members.UserRepository;
import com.nullexceptional.digibooky.domain.members.dto.CreateUserDto;
import com.nullexceptional.digibooky.domain.members.dto.UserDto;
import com.nullexceptional.digibooky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/users")
public class UserResource {
    private UserRepository userRepository;
    private UserService userService;

    @Autowired
    public UserResource(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody CreateUserDto createUserDto) {
        User user = new User(createUserDto.getInss(), createUserDto.getFirstName(), createUserDto.getLastName(), createUserDto.getEmail(), createUserDto.getAddress());
        return UserDto.toUserDTO(userRepository.saveUser(user));
    }

    @GetMapping(produces = "application/json", path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable UUID id) {
        return UserDto.toUserDTO(userRepository.getUserById(id));
    }


}
