package com.nullexceptional.digibooky.domain.members.dto;

import com.nullexceptional.digibooky.domain.members.Address;
import com.nullexceptional.digibooky.domain.members.Role;
import com.nullexceptional.digibooky.domain.members.User;

import java.util.UUID;

public class UserDto {
    private final UUID id;
    private final String inss;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private boolean logged;
    private Role role;

    private UserDto(UUID id, String inss, String firstName, String lastName, String email, Address address, boolean logged, Role role) {
        this.id = id;
        this.inss = inss;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.logged = logged;
        this.role = role;
    }

    public static UserDto toUserDTO(User user) {
        return new UserDto(user.getId(), user.getInss(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAddress(), user.isLogged(), user.getRole());
    }

    public UUID getId() {
        return id;
    }

    public String getInss() {
        return inss;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public boolean isLogged() {
        return logged;
    }

    public Role getRole() {
        return role;
    }
}
