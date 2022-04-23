package com.tattooshop.shop.web.controller;

import com.tattooshop.shop.model.Category;
import com.tattooshop.shop.model.Tattoo;
import com.tattooshop.shop.service.CategoryService;
import com.tattooshop.shop.service.TattooService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("tattoos")
public class TattooController {

    private final TattooService tattooService;
    private final CategoryService categoryService;

    public TattooController(TattooService tattooService, CategoryService categoryService) {
        this.tattooService = tattooService;
        this.categoryService = categoryService;
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
    public String addProductPage(Model model) {
        List<Category> categories = this.categoryService.listCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "add-tattoo");
        return "master-template";
    }
}
