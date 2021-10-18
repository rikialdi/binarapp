package com.binar.binar.controller.tymeleaf;


import com.binar.binar.entity.Jenis;
import com.binar.binar.entity.oauth.User;
import com.binar.binar.repository.oauth.UserRepository;
import com.binar.binar.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.binar.binar.utils.ResourceNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller ///
@RequestMapping("/user-register/web/")
public class RegisterConfim {
    @Autowired
    public UserRepository userRepo;
    @GetMapping(value = "{tokenotp}")
    public String getJenisById(@PathVariable String  tokenotp) {
        User user = userRepo.findOneByOTP(tokenotp);
            if (null != user) {
                System.out.println("user null: tidak ditemukan");
            }
        user.setEnabled(true);
            userRepo.save(user);
        return "register";
    }
}
