package ru.nsu.ccfit.db.hardwarestore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/cash")
public class CashController {
    //TODO написать логику взятия денег и пополнения


    @GetMapping
    public String getTopUpMoneyPage(Model model) {
        return "cash";
    }

    public String topUpMoney(Model model) {
        return "";
    }
}
