package com.wizer.test.wizer.assessment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping(path = {"/dashboard","/book","/category"})
    public ModelAndView home() {
        return new ModelAndView("redirect:/");
    }
}
