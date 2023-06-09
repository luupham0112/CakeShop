package com.phanmem.cakeshop.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CustomerDto {
    @Size(min = 3, max = 15, message = "First name should have 3-15 characters")
    private String firstName;

    @Size(min = 3, max = 15, message = "Last name should have 3-15 characters")
    private String lastName;

    private String userName;

    @Size(min = 5, max = 20, message = "Password should have 5-20 characters")
    private String passWord;

    private String repeatPassword;
}
