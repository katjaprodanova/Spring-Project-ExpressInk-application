package com.tattooshop.shop.web.controller;

import com.tattooshop.shop.model.Category;
import com.tattooshop.shop.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {

        this.categoryService = categoryService;
    }

    @GetMapping
    public String listAllCategories(Model model){

        List<Category> categories = categoryService.listCategories();
        model.addAttribute("categories",categories);
        model.addAttribute("bodyContent","categories");
        return "master-template";
    }
}
