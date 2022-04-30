package com.tattooshop.shop.web.controller;

import com.tattooshop.shop.model.Category;
import com.tattooshop.shop.model.Role;
import com.tattooshop.shop.model.Tattoo;
import com.tattooshop.shop.model.User;
import com.tattooshop.shop.model.exceptions.TattooNotFoundException;
import com.tattooshop.shop.repository.TattooRepository;
import com.tattooshop.shop.service.CategoryService;
import com.tattooshop.shop.service.TattooService;
import com.tattooshop.shop.service.UserService;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/tattoos")
public class TattooController {

    private final TattooService tattooService;
    private final TattooRepository tattooRepository;
    private final CategoryService categoryService;
    private final UserService userService;

    public TattooController(TattooService tattooService,TattooRepository tattooRepository, CategoryService categoryService,UserService userService) {
        this.tattooService = tattooService;
        this.tattooRepository=tattooRepository;
        this.categoryService = categoryService;
        this.userService=userService;
    }

    @GetMapping
    public String getTattooPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Tattoo> tattoos = this.tattooService.findAll();
        model.addAttribute("tattoos", tattoos);
        model.addAttribute("bodyContent", "all-tattoos");
        return "master-template";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addTattooForm(Model model) {
        List<Category> categories = this.categoryService.listCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "add-tattoo");
        return "master-template";
    }

    @PostMapping("/add")
    public String addOrEditTattoo(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Double price,
            @RequestParam Long category,
            @RequestParam String imgUrl,
            HttpServletRequest http
           // @RequestParam String artist
            ) {
        if (id != null) {
            this.tattooService.edit(id, name,description, price, category, http.getRemoteUser(),imgUrl);
        } else {
            this.tattooService.save(name,description, price, category, http.getRemoteUser(),imgUrl);
        }
        return "redirect:/tattoos";
    }

    @PostMapping("/delete/{id}")
    public String deleteTattoo(@PathVariable Long id) {
        this.tattooRepository.deleteById(id);
        return "redirect:/tattoos";
    }

    @GetMapping("/edit-form/{id}")
    public String editTattooForm(@PathVariable Long id, Model model) {
        if (this.tattooService.findById(id).isPresent()) {
            Tattoo tattoo = this.tattooService.findById(id).orElseThrow(()-> new TattooNotFoundException(id));
            List<Category> categories = this.categoryService.listCategories();
//            List<User> artists = this.userService.findAll();
            model.addAttribute("categories", categories);
//            model.addAttribute("artists",artists);
            model.addAttribute("tattoo", tattoo);
            model.addAttribute("bodyContent", "edit-tattoo");
            return "master-template";
        }
        return "redirect:/tattoos?error=TattooNotFound";
    }
    @GetMapping("/artistsss")
    public String getArtists(Model model){
        List<User> userList = this.userService.findUserByRole(Role.ROLE_ARTIST);
        model.addAttribute("artists",userList);
        model.addAttribute("bodyContent","artists");
        return "master-template";

    }
}
