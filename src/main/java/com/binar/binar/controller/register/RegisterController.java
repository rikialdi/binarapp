package com.binar.binar.controller.register;

import com.binar.binar.config.Config;
import com.binar.binar.controller.forgetpassword.*;
import com.binar.binar.entity.oauth.User;
import com.binar.binar.repository.oauth.UserRepository;
import com.binar.binar.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/user-register/")
public class RegisterController {

    @Autowired
    public UserRepository repoUser;
    TemplateCRUD templateCRUD = new TemplateCRUD();

    @Autowired
    private TokenStore tokenStore;

    Config config = new Config();

    @Autowired
    public UserService serviceReq;


    @Autowired
    public EmailSender emailSender;

    @Autowired
    public EmailTemplate emailTemplate;

    @Autowired
    public PasswordEncoder passwordEncoder;


    TemplateReqRes templateReqRes = new TemplateReqRes();

    public void  getTokenStore(){
       // get token :
        //  LOGUT di BE :
    }

    @Autowired
    public UserRepository userRepository;
    @Value("${expired.token.password.minute:}")//FILE_SHOW_RUL
    private int expiredToken;

    @PostMapping("register")
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map> save(@Valid @RequestBody RegisterModel objModel) throws RuntimeException {
        Map map = new HashMap();

        User user = repoUser.checkExistingEmail(objModel.getEmail());
        if (null != user) {
            return new ResponseEntity<Map>(templateCRUD.notFound("Username" + config.message_alreadyexist), HttpStatus.OK);
        }
        map = serviceReq.registerManual(objModel);
        UserUpdateModel  obk = new UserUpdateModel();
        obk.setEmail(objModel.getEmail());
        sendEmailegister(obk);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }


    // Step 2: sendp OTP berupa URL: guna updeta enable agar bisa login:
    @PostMapping("send-email")//send OTP
    public Map sendEmailegister(@RequestBody UserUpdateModel user) {
        String message = "Thanks, please check your email";

        if (user.getEmail() == null) return templateReqRes.isRequired("No email provided");
        User found = userRepository.findOneByUsername(user.getEmail());
        if (found == null) return templateReqRes.notFound("Email not found"); //throw new BadRequest("Email not found");

        String template = emailTemplate.getRegister();
        if (StringUtils.isEmpty(found.getOtp())) {
            User search;
            String otp;
            do {
                otp = SimpleStringUtils.randomString(6, true);
                search = userRepository.findOneByOTP(otp);
            } while (search != null);
            Date dateNow = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateNow);
            calendar.add(Calendar.MINUTE, expiredToken);
            Date expirationDate = calendar.getTime();

            found.setOtp(otp);
            found.setOtpExpiredDate(expirationDate);
            template = template.replaceAll("\\{\\{REGISTER}}", "http://localhost:8080/api/user-register/web/index/"+otp);
            userRepository.save(found);
        } else {
            template = template.replaceAll("\\{\\{REGISTER}}", "http://localhost:8080/api/user-register/web/index/"+found.getOtp());
        }
        emailSender.sendAsync(found.getUsername(), "Binar - Register", template);
        return templateReqRes.template1("success");
    }
}
