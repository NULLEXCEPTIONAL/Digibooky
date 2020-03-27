package com.nullexceptional.digibooky.domain.members.dtos;

import com.nullexceptional.digibooky.domain.members.Address;

public class CreateUserDto {
    private String inss;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;

    public void setInss(String inss) {
        this.inss = inss;
    }

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
