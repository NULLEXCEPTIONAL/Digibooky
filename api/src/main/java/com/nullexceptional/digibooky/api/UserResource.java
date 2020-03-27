package com.nullexceptional.digibooky.api;

import com.nullexceptional.digibooky.domain.members.User;
import com.nullexceptional.digibooky.domain.members.dto.CreateUserDto;
import com.nullexceptional.digibooky.domain.members.dto.UserDto;
import com.nullexceptional.digibooky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UserResource {
    private UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody CreateUserDto createUserDto) {
        User user = new User(createUserDto.getInss(), createUserDto.getFirstName(), createUserDto.getLastName(), createUserDto.getEmail());
        userService.addUser(user);
        return UserDto.toUserDTO(user);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@RequestBody UserDto userDto){
       return userService.getUserById(userDto.getId());
    }


}
