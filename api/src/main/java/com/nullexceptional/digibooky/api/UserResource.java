package com.nullexceptional.digibooky.api;

import com.nullexceptional.digibooky.domain.members.dto.CreateUserDto;
import com.nullexceptional.digibooky.domain.members.dto.UserDto;
import com.nullexceptional.digibooky.domain.members.exceptions.ApiEmailDuplicationExceptionRequest;
import com.nullexceptional.digibooky.domain.members.exceptions.ApiEmailRequestException;
import com.nullexceptional.digibooky.domain.members.exceptions.DuplicationInssException;
import com.nullexceptional.digibooky.service.members.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

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
    public UserDto createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        isValidEmailAddress(createUserDto.getEmail());
        isEmailUnique(createUserDto);
        isInssNotUnique(createUserDto);
        UserDto userDto = new UserDto(createUserDto.getInss(), createUserDto.getFirstName(), createUserDto.getLastName(), createUserDto.getEmail(), createUserDto.getAddress());
        userService.addUser(userDto);
        UserDto userById = userService.getUserById(userDto.getId());
        System.out.println(userById.getId());
        System.out.println(userDto.getId());
        return userDto;
    }

    private void isInssNotUnique(CreateUserDto createUserDto) {
        boolean isInssUnique = userService.getAllUsers().stream()
                .anyMatch(user -> user.getInss().equals(createUserDto.getInss()));
        if (isInssUnique) {
            throw new DuplicationInssException("This inss number already exists. This is a custom message");
        }
    }


    @GetMapping(produces = "application/json", path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    private void isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        if (!m.matches()) {
            throw new ApiEmailRequestException("E-mail is not valid. This is a custom exception");
        }
    }

    private void isEmailUnique(CreateUserDto createUserDto) {
        boolean isEmailUnique = userService.getAllUsers().stream()
                .anyMatch(x -> x.getEmail().equals(createUserDto.getEmail()));
        if (isEmailUnique) {
            throw new ApiEmailDuplicationExceptionRequest("E-mail already exists in our system. Of course this is a custom message");
        }
    }

}
