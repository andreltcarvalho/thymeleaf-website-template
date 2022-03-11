package com.template.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PrivacyController {

    @GetMapping("/privacy")
    public ModelAndView about() {
        return new ModelAndView("privacy");
    }
}