package com.tattooshop.shop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/logoutt")
public class LogoutController {

    @GetMapping
    public String logout(HttpServletRequest request, Model model) {
        model.addAttribute("username", request.getParameter("username").toString());
        model.addAttribute("bodyContent","loggedout");
        request.getSession().invalidate();
        return "master-template";
    }

    @GetMapping("/success")
    public String logoutsuccess(Model model){
        model.addAttribute("bodyContent","loggedout");
        return "master-template";
    }
}
