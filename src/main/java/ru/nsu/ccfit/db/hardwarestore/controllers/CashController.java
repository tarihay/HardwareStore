package ru.nsu.ccfit.db.hardwarestore.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.ccfit.db.hardwarestore.services.UserService;
import ru.nsu.ccfit.db.hardwarestore.utils.SecurityUtils;

@Controller
@RequestMapping("/api/v1/cash")
@AllArgsConstructor
public class CashController {
    private final UserService userService;

    @GetMapping
    public String getTopUpMoneyPage(Model model) {
        return "cash";
    }

    @PostMapping("/top-up")
    public String topUpCash(@RequestParam("amount") Double amount,
                            Model model) {
        String email = SecurityUtils.getSessionUser();
        userService.addCash(email, amount);
        return "redirect:/api/v1/personal-area";
    }
}
