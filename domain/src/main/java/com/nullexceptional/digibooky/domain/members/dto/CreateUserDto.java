package com.nullexceptional.digibooky.domain.members.dto;

import com.nullexceptional.digibooky.domain.members.Address;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateUserDto {
    @NotNull(message = "You must insert an inss number")
    @NotBlank(message = "You must insert an inss number")
    private String inss;
    private String firstName;
    private String lastName;
    @NotNull(message = "You must insert an e-mail")
    @NotBlank(message = "You must insert an e-mail")
    private String email;
    private Address address;



    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(Address address) {
        this.address = address;
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
}
