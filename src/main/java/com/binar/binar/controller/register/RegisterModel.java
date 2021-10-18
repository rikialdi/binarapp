package com.binar.binar.controller.register;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class RegisterModel {
    public Long id;

    @NotEmpty(message = "Email is required.")
    @Email(message = "Invalid Email address")
    public String email;

    public String username;
    @NotEmpty(message = "Password is required.")
    public String password;
    @NotEmpty(message = "FullName is required.")
    public String fullname;
}
