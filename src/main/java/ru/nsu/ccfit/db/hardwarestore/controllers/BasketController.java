package ru.nsu.ccfit.db.hardwarestore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/basket")
public class BasketController {

    @GetMapping
    public String getBasketPage(Model model) {
        return "basket";
    }
}
