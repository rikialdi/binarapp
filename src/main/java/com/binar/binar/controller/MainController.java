package com.binar.binar.controller;

import com.binar.binar.ResponseException.Forbidden;
import com.binar.binar.entity.oauth.User;
import com.binar.binar.repository.oauth.UserRepository;
import com.binar.binar.service.dbseeder.Oauth2UserDetailsService;
import com.binar.binar.service.dbseeder.RolePathChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/user")
public class MainController {
//localhost:8090/api/v1/user/oauth/authenticate?uri=/api/v1/product/listpage
    @Autowired
    public UserRepository repoUser;

    @Autowired
    private Oauth2UserDetailsService userDetailsService;

    @Autowired
    private RolePathChecker checker;

    @RequestMapping("/oauth/authenticate")
    public Map<String, Object> authenticateAction(
            @RequestParam Map<String, String> query,
            HttpServletResponse response,
            HttpServletRequest request,
            Principal principal) throws RuntimeException {

        String username = principal.getName();
        UserDetails user = null;
        String xUri = request.getHeader("X-Uri");
        if (StringUtils.isEmpty(xUri) && query.containsKey("uri")) xUri = query.get("uri");
        if (!StringUtils.isEmpty(username)) user = userDetailsService.loadUserByUsername(username);
        if (null == user) throw new UsernameNotFoundException("User not found");
        if (!checker.isAllow(user, xUri, request.getMethod()))throw new Forbidden("Not enough access to this endpoint");

        response.addHeader("X-User", user.getUsername());
        User idUser = repoUser.findOneByUsername(user.getUsername());
        if (null == idUser)throw new Forbidden("User id not found");

        Map<String, Object> userFound = new HashMap<>();
        userFound.put("data", idUser);
        return userFound;
    }
    }
