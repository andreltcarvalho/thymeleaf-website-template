package com.template.controllers;

import com.template.entities.UserEntity;
import com.template.services.SenderMailService;
import com.template.services.UserEntityService;
import com.template.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserEntityService userEntityService;

    @Autowired
    private SenderMailService mailService;

    @GetMapping("/login")
    public ModelAndView viewLoginPage() {
        ModelAndView login = new ModelAndView("login");
        if (HttpUtils.isLogged()) {
            return new ModelAndView("home");
        }
        login.addObject("login", "true");
        return login;
    }

    @GetMapping("/forgotPassword")
    public ModelAndView forgotPassword() {
        return new ModelAndView("forgotPassword");
    }


    @PostMapping("/forgotPassword")
    public ModelAndView changePassword(@RequestParam String email) {
        UserEntity user = userEntityService.findByEmail(email);
        if (user != null && user.isEnabled() == true) {
            mailService.sendNewPasswordEmail(user);
            return new ModelAndView("forgotPassword").addObject("messages", "Email enviado com sucesso!");
        } else {
            return new ModelAndView("forgotPassword").addObject("errors", "Email inválido ou conta inexistente!");
        }
    }

    @GetMapping("/newPassword")
    public ModelAndView getNewPassword(@RequestParam String verificationCode) {
        return new ModelAndView("newPassword").addObject("verificationCode", verificationCode);
    }

    @PostMapping("/newPassword")
    public ModelAndView postNewPassword(@RequestParam String verificationCode, @RequestParam String password) {
        UserEntity user = userEntityService.findByVerificationCode(verificationCode);
        if (user != null && user.isEnabled() == true) {
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            user.setVerificationCode(null);
            userEntityService.update(user);
            logger.info("Usuario " + user.getEmail() + " trocou de senha.");
            return new ModelAndView("newPassword").addObject("messages", Arrays.asList("Senha atualizada com sucesso!"));
        } else {
            return new ModelAndView("newPassword").addObject("errors", Arrays.asList("Erro ao atualizar a senha!"));
        }
    }
}