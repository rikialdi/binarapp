package com.binar.binar.controller.forgetpassword;

import com.binar.binar.config.Config;
import com.binar.binar.entity.oauth.User;
import com.binar.binar.repository.oauth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/forget-password/")
public class ForgetPassword {
    @Autowired
    public EmailSender emailSender;

    @Autowired
    public EmailTemplate emailTemplate;

    @Autowired
    public PasswordEncoder passwordEncoder;

    Config config = new Config();
    TemplateReqRes templateReqRes = new TemplateReqRes();

    @Autowired
    public UserRepository userRepository;
    @Value("${expired.token.password.minute:}")//FILE_SHOW_RUL
    private int expiredToken;

    @PostMapping("/forgot-password")//send OTP
    public Map sendEmailPassword(@RequestBody UserUpdateModel user) {
        String message = "Thanks, please check your email";

        if (user.getEmail() == null) return templateReqRes.isRequired("No email provided");
        User found = userRepository.findOneByUsername(user.getEmail());
        if (found == null) return templateReqRes.notFound("Email not found"); //throw new BadRequest("Email not found");

        String template = emailTemplate.getResetPassword();
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
            template = template.replaceAll("\\{\\{PASS_TOKEN}}", otp);
            userRepository.save(found);
        } else {
            template = template.replaceAll("\\{\\{PASS_TOKEN}}", found.getOtp());
        }
        emailSender.sendAsync(found.getUsername(), "Chute - Forget Password", template);


        return templateReqRes.template1("success");

    }

    @PostMapping("/forgot-password-reset")//ganti password
    public Map<String, String> resetPassword(@RequestBody UserUpdateModel model) {
        if (model.getOtp() == null) return templateReqRes.notFound("Token " + config.isRequired);
        if (model.getNewPassword() == null) return templateReqRes.notFound("New Password " + config.isRequired);
        User user = userRepository.findOneByOTP(model.getOtp());
        String success;
        if (user == null) return templateReqRes.notFound("Token not valid");

        user.setPassword(passwordEncoder.encode(model.getNewPassword().replaceAll("\\s+", "")));
        user.setOtpExpiredDate(null);
        user.setOtp(null);

        try {
            userRepository.save(user);
            success = "success";
        } catch (Exception e) {
            return templateReqRes.template1(e.getMessage());
        }
        return templateReqRes.template1(success);
    }

}
