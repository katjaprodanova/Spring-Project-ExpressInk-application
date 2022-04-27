package com.tattooshop.shop.web.controller;

import com.tattooshop.shop.model.Favorites;
import com.tattooshop.shop.model.User;
import com.tattooshop.shop.service.FavoritesService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("favorites")
public class FavoritesController {

    private final FavoritesService favoritesService;

    public FavoritesController(FavoritesService favoritesService) {
        this.favoritesService = favoritesService;
    }



    @GetMapping
    public String getFavoritesPage(@RequestParam(required = false) String error,
                                      HttpServletRequest req,
                                      Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String username = req.getRemoteUser();
        Favorites favorites = this.favoritesService.getActiveFavorites(username);
        model.addAttribute("tattoos", this.favoritesService.listAllTattoosInFavorites(favorites.getId()));
        model.addAttribute("bodyContent", "favorites");
        return "master-template";
    }

    @PostMapping("/add-tattoo/{id}")
    public String addTattooToFavs(@PathVariable Long id, HttpServletRequest req, Authentication authentication) {
        try {
           // User user = (User) authentication.getPrincipal();
            this.favoritesService.addTattooToFavorites(req.getRemoteUser(), id);
            return "redirect:/favorites";
        } catch (RuntimeException exception) {
            return "redirect:/favorites?error=" + exception.getMessage();
        }
    }

    @PostMapping("/delete-fav/{id}")
    public String deleteTattooFromFav(@PathVariable Long id, HttpServletRequest req){
         this.favoritesService.deleteById(id);
         return "redirect:/favorites";
    }

}
