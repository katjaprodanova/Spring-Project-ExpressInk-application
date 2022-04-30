package com.tattooshop.shop.web.controller;

import com.tattooshop.shop.model.Role;
import com.tattooshop.shop.model.User;
import com.tattooshop.shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/artists")
public class ArtistController {

    private final UserService userService;

    public ArtistController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllArtists(Model model){
        List<User> artists = this.userService.findUserByRole(Role.ROLE_ARTIST);
        model.addAttribute("artists",artists);
        model.addAttribute("bodyContent","artists");
        return "master-template";
    }
/*
    @GetMapping("/schedule")
    public String scheduleAppointmentPage(Model model){
        model.addAttribute("bodyContent", "")

    }*/
}
