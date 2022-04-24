package com.tattooshop.shop.web.controller;

import com.tattooshop.shop.model.Category;
import com.tattooshop.shop.model.Tattoo;
import com.tattooshop.shop.model.User;
import com.tattooshop.shop.service.CategoryService;
import com.tattooshop.shop.service.TattooService;
import com.tattooshop.shop.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tattoos")
public class TattooController {

    private final TattooService tattooService;
    private final CategoryService categoryService;
    private final UserService userService;

    public TattooController(TattooService tattooService, CategoryService categoryService,UserService userService) {
        this.tattooService = tattooService;
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
            @RequestParam Long category
           // @RequestParam String artist
            ) {
        if (id != null) {
            this.tattooService.edit(id, name,description, price, category, "artist");
        } else {
            this.tattooService.save(name,description, price, category, "artist");
        }
        return "redirect:/tattoos";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.tattooService.deleteById(id);
        return "redirect:/tattoos";
    }

    @GetMapping("/edit-form/{id}")
    public String editTattooForm(@PathVariable Long id, Model model) {
        if (this.tattooService.findById(id).isPresent()) {
            Tattoo tattoo = this.tattooService.findById(id).get();
            List<Category> categories = this.categoryService.listCategories();
            List<User> artists = this.userService.findAll();
            model.addAttribute("categories", categories);
            model.addAttribute("artists",artists);
            model.addAttribute("tattoo", tattoo);
            model.addAttribute("bodyContent", "add-tattoo");
            return "master-template";
        }
        return "redirect:/tattoos?error=TattooNotFound";
    }
}
