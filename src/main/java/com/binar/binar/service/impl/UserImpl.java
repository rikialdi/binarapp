package com.binar.binar.service.impl;

import com.binar.binar.config.Config;
import com.binar.binar.controller.register.RegisterModel;
import com.binar.binar.controller.register.TemplateCRUD;
import com.binar.binar.entity.oauth.Role;
import com.binar.binar.entity.oauth.User;
import com.binar.binar.repository.oauth.RoleRepository;
import com.binar.binar.repository.oauth.UserRepository;
import com.binar.binar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserImpl implements UserService {
    Config config = new Config();
    @Autowired
    RoleRepository repoRole;
    private static final Logger logger = LoggerFactory.getLogger(UserImpl.class);


    @Autowired
    UserRepository repoUser;


    @Autowired
    private PasswordEncoder encoder;

    TemplateCRUD templateCRUD = new TemplateCRUD();


    @Override
    public Map registerManual(RegisterModel objModel) {
        Map map = new HashMap();
        try {
            String[] roleNames = {"ROLE_USER", "ROLE_READ", "ROLE_WRITE"};
            User user = new User();
            user.setPassword(objModel.getPassword());
            user.setUsername(objModel.getEmail());
            user.setFullname(objModel.getFullname());
            //step 1 :
            user.setEnabled(false); // matikan user
            if (objModel.getUsername() != null) {
                user.setUsername1(objModel.getUsername());
            }

            //Validation belum

            String password = encoder.encode(user.getPassword().replaceAll("\\s+", ""));
            List<Role> r = repoRole.findByNameIn(roleNames);

            user.setRoles(r);
            user.setPassword(password);
            User obj = repoUser.save(user);

            map.put("data", obj);
            map.put(config.getCode(), config.code_sukses);
            map.put(config.getMessage(), config.message_sukses);
            return map;

        } catch (Exception e) {
            logger.error("Eror registerManual=", e);
            map.put(config.getCode(), config.code_server);
            map.put(config.getMessage(), e.getLocalizedMessage());
            return map;

        }  }

    }
