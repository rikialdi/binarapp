package com.binar.binar.controller.forgetpassword;

import lombok.Data;

@Data
public class UserUpdateModel {

    public Long id;
    public String email;
    public String otp;
    public String username;
    public String image;
    public String newPassword;
}

