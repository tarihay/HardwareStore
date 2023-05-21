package ru.nsu.ccfit.db.hardwarestore.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.UserEntity;
import ru.nsu.ccfit.db.hardwarestore.services.BasketService;
import ru.nsu.ccfit.db.hardwarestore.services.UserService;
import ru.nsu.ccfit.db.hardwarestore.utils.SecurityUtils;

@Controller
@RequestMapping("/api/v1/basket")
@AllArgsConstructor
public class BasketController {
    private BasketService basketService;
    private UserService userService;

    @GetMapping
    public String getBasketPage(Model model) {
        String username = SecurityUtils.getSessionUser();
        if (username != null) {
            model.addAttribute("basket", basketService.getBasketByOwner(username));
            return "basket";
        }

        return "redirect:/login";
    }


}
