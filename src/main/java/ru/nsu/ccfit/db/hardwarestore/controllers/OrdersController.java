package ru.nsu.ccfit.db.hardwarestore.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated.OrderDetailsEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductEntity;
import ru.nsu.ccfit.db.hardwarestore.services.OrderService;
import ru.nsu.ccfit.db.hardwarestore.utils.SecurityUtils;

@Controller
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrdersController {
    private OrderService orderService;

    @GetMapping
    public String getOrdersPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model
    ) {
        String email = SecurityUtils.getSessionUser();
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<OrderDetailsEntity> ordersPage = orderService.getUserOrders(email, pageable);
        model.addAttribute("orders", ordersPage.getContent());
        model.addAttribute("totalPages", ordersPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        return "orders";
    }

    @GetMapping("/{orderId}")
    public String getOrderById(
            @PathVariable Long orderId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "7") int size,
            Model model
    ) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ProductEntity> products = orderService.getOrderById(orderId, pageable);
        model.addAttribute("products", products.getContent());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        return "order-products";
    }
}
