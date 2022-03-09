package com.template.controllers;

import com.template.entities.UserEntity;
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
public class ProfileController {

    private static Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    private UserEntityService service;


    @GetMapping("/profile")
    public ModelAndView profilePage() {
        if (!HttpUtils.isLogged()) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("profile");
    }

    @GetMapping("/profile/home")
    public ModelAndView homePage() {
        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/profile/changePassword")
    public ModelAndView changePassword(@RequestParam String email, @RequestParam String senhaAntiga, @RequestParam String senhaNova) {
        if (!HttpUtils.isLogged()) {
            return new ModelAndView("redirect:/home");
        }
        UserEntity user = service.findByEmail(email);
        if (service.checkValidPassword(user.getPassword(), senhaAntiga)) {
            user.setPassword(new BCryptPasswordEncoder().encode(senhaNova));
            service.update(user);
            logger.info("Usuario " + user.getEmail() + " trocou de senha.");
            return new ModelAndView("profile").addObject("messages", Arrays.asList("A senha foi alterada com sucesso!"));
        } else {
            return new ModelAndView("profile").addObject("errors", Arrays.asList("A Senha antiga foi digitada incorretamente! Tente novamente"));
        }
    }
}
