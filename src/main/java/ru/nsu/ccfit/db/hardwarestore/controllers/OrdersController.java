package ru.nsu.ccfit.db.hardwarestore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.UserEntity;
import ru.nsu.ccfit.db.hardwarestore.utils.SecurityUtils;

@Controller
@RequestMapping("/api/v1/orders")
public class OrdersController {
    //TODO написать логику для заказов

    @GetMapping
    public String getOrdersPage(Model mode) {
        UserEntity user = new UserEntity();
        String username = SecurityUtils.getSessionUser();
        return "orders";
    }
}
