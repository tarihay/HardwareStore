package ru.nsu.ccfit.db.hardwarestore.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.UserEntity;
import ru.nsu.ccfit.db.hardwarestore.services.UserService;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

@Controller
@RequestMapping("/api/v1/personal-area")
@AllArgsConstructor
public class PersonalAreaController {
    private final UserService userService;

    @GetMapping
    public String getAreaPage(Model model) throws ParseException {
        //TODO сделать по-нормальному, когда будет добавллено jwt
        UserEntity userEntity = new UserEntity("tarihay", "abcd", "Игорь", "Горин", LocalDate.parse("2002-08-14"));
        model.addAttribute("user", userEntity);
        return "personal-area";
    }
}
