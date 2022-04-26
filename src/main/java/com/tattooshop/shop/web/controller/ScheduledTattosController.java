//
//package com.tattooshop.shop.web.controller;
//
//import com.tattooshop.shop.model.ScheduledTattoos;
//import com.tattooshop.shop.service.ScheduledTattoosService;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Controller
//@RequestMapping("scheduled")
//public class ScheduledTattosController {
//
//    private final ScheduledTattoosService scheduledTattoosService;
//
//    public ScheduledTattosController(ScheduledTattoosService scheduledTattoosService) {
//        this.scheduledTattoosService = scheduledTattoosService;
//    }
//
//    @GetMapping
//    public String getScheduledPage(@RequestParam(required = false) String error,
//                                      HttpServletRequest req,
//                                      Model model) {
//        if (error != null && !error.isEmpty()) {
//            model.addAttribute("hasError", true);
//            model.addAttribute("error", error);
//        }
//        String username = req.getRemoteUser();
//        ScheduledTattoos scheduledTattoos = this.scheduledTattoosService.findByUser(this.us);
//        model.addAttribute("products", this.shoppingCartService.listAllProductsInShoppingCart(shoppingCart.getId()));
//        model.addAttribute("bodyContent", "shopping-cart");
//        return "master-template";
//    }
//
//    @PostMapping("/add-tattoo/{id}")
//    public String addProductToShoppingCart(@PathVariable Long id, HttpServletRequest req, Authentication authentication) {
//        try {
//            User user = (User) authentication.getPrincipal();
//            this.shoppingCartService.addProductToShoppingCart(user.getUsername(), id);
//            return "redirect:/shopping-cart";
//        } catch (RuntimeException exception) {
//            return "redirect:/shopping-cart?error=" + exception.getMessage();
//        }
//    }
//}
//
