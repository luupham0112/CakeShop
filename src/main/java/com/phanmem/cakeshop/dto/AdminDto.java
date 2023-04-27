package com.phanmem.cakeshop.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class AdminDto  {

    @Size(min = 3, max = 10, message = "Invalid user name!(3-10 characters)")
    @NotEmpty(message = "username not invalid")
    private String userName;
//    @Email(message = "email khong hop le")
    private  String email;
    @Size(min = 5, max = 15, message = "Invalid password !(5-15 characters)")
    private String passWord;
    private  String repeatPassword;



}
