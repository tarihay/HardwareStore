package ru.nsu.ccfit.db.hardwarestore.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.productRelated.ProductDTO;
import ru.nsu.ccfit.db.hardwarestore.services.CartService;
import ru.nsu.ccfit.db.hardwarestore.services.OrderService;
import ru.nsu.ccfit.db.hardwarestore.services.UserService;
import ru.nsu.ccfit.db.hardwarestore.utils.SecurityUtils;

import java.math.BigDecimal;
import java.util.Objects;

@Controller
@RequestMapping("/api/v1/cart")
@AllArgsConstructor
public class CartController {
    private CartService cartService;
    private OrderService orderService;
    private UserService userService;

    @GetMapping
    public String getCartPage(
            Model model,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "7") int size
    ) {
        String username = SecurityUtils.getSessionUser();
        if (username != null) {
            Pageable pageable = PageRequest.of(page - 1, size);
            Page<ProductDTO> products = cartService.getCartByOwner(username, pageable);
            BigDecimal cartTotal = cartService.getTotalPrice(username);
            model.addAttribute("totalPrice", cartTotal);
            model.addAttribute("products", products.getContent());
            model.addAttribute("totalPages", products.getTotalPages());
            model.addAttribute("currentPage", page);
            model.addAttribute("errorText", "Либо корзина пуста, либо денег нема");
            model.addAttribute("size", size);
            return "cart";
        }

        return "redirect:/login";
    }

    @PostMapping("/remove/{productId}")
    public String addProductToCart(
            @PathVariable long productId,
            HttpServletRequest request
    ) {
        String email = SecurityUtils.getSessionUser();
        cartService.removeItemFromUsersCart(email, productId);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @GetMapping("/make-order")
    public String createOrder(Model model) {
        String email = SecurityUtils.getSessionUser();
        String result = orderService.createOrder(email);
        String errorText;
        if (Objects.equals(result, "money")) {
            errorText = "Недостаточно средств для покупки";
            model.addAttribute("errorText", errorText);
            return "redirect:/api/v1/cart?fail";
        }
        if (Objects.equals(result, "no-items")) {
            errorText = "В корзине нет товаров";
            model.addAttribute("errorText", errorText);
            return "redirect:/api/v1/cart?fail";
        }

        return "redirect:/api/v1/orders";
    }

}
