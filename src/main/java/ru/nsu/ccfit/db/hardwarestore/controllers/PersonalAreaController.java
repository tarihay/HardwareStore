package ru.nsu.ccfit.db.hardwarestore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/personal-area")
public class PersonalAreaController {
    @GetMapping
    public String getAreaPage(Model model) {
        return "personal-area";
    }
}
