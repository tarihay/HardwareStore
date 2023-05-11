package ru.nsu.ccfit.db.hardwarestore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1")
public class MainController {

    @GetMapping
    public String getMainPage(Model model) {
        return "main";
    }
}
