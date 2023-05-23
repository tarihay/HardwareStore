package ru.nsu.ccfit.db.hardwarestore.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.userRelated.UserDTO;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.UserEntity;
import ru.nsu.ccfit.db.hardwarestore.services.UserService;
import ru.nsu.ccfit.db.hardwarestore.utils.SecurityUtils;

import java.text.ParseException;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/api/v1/personal-area")
@AllArgsConstructor
public class PersonalAreaController {
    private final UserService userService;

    @GetMapping
    public String getAreaPage(Model model) throws ParseException {
        String email = SecurityUtils.getSessionUser();
        if (email == null) {
            return "redirect:/api/v1/login";
        }
        log.info(email);
        UserDTO user = userService.getUserDTOByEmail(email);
        model.addAttribute("user", user);
        return "personal-area";
    }

    @GetMapping("/edit")
    public String getEditPage(Model model) {
        String email = SecurityUtils.getSessionUser();
        if (email == null) {
            return "redirect:/api/v1/login";
        }
        UserDTO user = userService.getUserDTOByEmail(email);
        model.addAttribute("user", user);
        return "personal-area-edit";
    }

    @PostMapping("/edit/save")
    public String edit(@ModelAttribute("user") UserDTO userDTO,
                           BindingResult result, Model model) {
        UserEntity user = userService.getUserByUsername(userDTO.getUsername());

        UserEntity existingUserEmail = userService.getUserByEmail(userDTO.getEmail());
        if (!Objects.equals(user.getEmail(), existingUserEmail.getEmail())) {
            if(!existingUserEmail.getEmail().isEmpty() && !existingUserEmail.getEmail().isEmpty()) {
                result.rejectValue("email", "Этот email уже используется");
                return "redirect:/api/v1/personal-area/edit?fail";
            }
        }
        if ((!userDTO.getPassword().isEmpty() || !userDTO.getPasswordCheck().isEmpty())
                && !Objects.equals(userDTO.getPassword(), userDTO.getPasswordCheck())) {
            result.rejectValue("password", "Пароли не совпадают");
            return "redirect:/api/v1/personal-area/edit?fail";
        }
        if (userDTO.getFirstname() == null) {
            return "redirect:/api/v1/personal-area/edit?fail";
        }
        if(result.hasErrors()) {
            model.addAttribute("user", userDTO);
            return "personal-area-edit";
        }

        user.setEmail(userDTO.getEmail());
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        if (!userDTO.getPassword().isEmpty()) {
            user.setPassword(userDTO.getPassword());
        }

        userService.updateUser(user);
        return "redirect:/api/v1/personal-area";
    }


}
