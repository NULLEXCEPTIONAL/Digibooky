package com.nullexceptional.digibooky.api;

import com.nullexceptional.digibooky.domain.members.Role;
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
        userService.checkInssDuplication(createUserDto.getInss());
        userService.checkIfEmailIsUnique(createUserDto.getEmail());
        userService.checkIfEmailIsValid(createUserDto.getEmail());
        UserDto userDto = new UserDto(createUserDto.getInss(), createUserDto.getFirstName(), createUserDto.getLastName(), createUserDto.getEmail(), createUserDto.getAddress());
        userService.addUser(userDto);
        return userDto;
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping( path="/librarian",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createLibrarian(@Valid @RequestBody CreateUserDto createUserDto) {
        userService.checkInssDuplication(createUserDto.getInss());
        userService.checkIfEmailIsUnique(createUserDto.getEmail());
        userService.checkIfEmailIsValid(createUserDto.getEmail());
        UserDto librarian = new UserDto(createUserDto.getInss(), createUserDto.getFirstName(), createUserDto.getLastName(), createUserDto.getEmail(), createUserDto.getAddress());
        librarian.setRole(Role.Librarian);
        userService.addUser(librarian);
        return librarian;
    }


    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping(produces = "application/json", path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }


}
