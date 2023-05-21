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

import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/api/v1")
@AllArgsConstructor
public class AuthController {
    private UserService userService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@ModelAttribute("user") UserDTO userDTO,
                           BindingResult result, Model model) {
        if (
                userDTO.getFirstname().isEmpty()
                || userDTO.getUsername().isEmpty()
                || userDTO.getEmail().isEmpty()
                || userDTO.getPassword().isEmpty()
                || userDTO.getPasswordCheck().isEmpty()
        ) {
            return "redirect:/api/v1/register?fail";
        }

        UserEntity existingUserEmail = userService.getUserByEmail(userDTO.getEmail());
        if(existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
            result.rejectValue("email", "Пользователь с таким email уже существует");
            return "redirect:/api/v1/register?fail";
        }
        UserEntity existingUserUsername = userService.getUserByUsername(userDTO.getUsername());
        if(existingUserUsername != null && existingUserUsername.getUsername() != null && !existingUserUsername.getUsername().isEmpty()) {
            result.rejectValue("username", "Пользователь с таким логином уже существует");
            return "redirect:/api/v1/register?fail";
        }
        if (!Objects.equals(userDTO.getPassword(), userDTO.getPasswordCheck())) {
            result.rejectValue("password", "Пароли не совпадают");
            return "redirect:/api/v1/register?fail";
        }
        if(result.hasErrors()) {
            model.addAttribute("user", userDTO);
            return "register";
        }
        userService.saveUser(userDTO);
        return "redirect:/api/v1";
    }
}
