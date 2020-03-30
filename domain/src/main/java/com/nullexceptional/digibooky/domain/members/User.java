package com.nullexceptional.digibooky.domain.members;

import java.util.UUID;

public class User {
    private final UUID id;
    private final String inss;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private boolean logged;
    private Role role;

    public User(UUID id, String inss, String firstName, String lastName, String email, Address address) {
        this.id = id;
        this.inss = inss;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.logged = false;
        this.role = Role.Member;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setRole(Role role) {
        this.role = role;
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
