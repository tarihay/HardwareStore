package ru.nsu.ccfit.db.hardwarestore.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.productRelated.ProductDTO;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductEntity;
import ru.nsu.ccfit.db.hardwarestore.services.CartService;
import ru.nsu.ccfit.db.hardwarestore.services.UserService;
import ru.nsu.ccfit.db.hardwarestore.utils.SecurityUtils;

@Controller
@RequestMapping("/api/v1/cart")
@AllArgsConstructor
public class CartController {
    private CartService cartService;
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
            model.addAttribute("products", products.getContent());
            model.addAttribute("totalPages", products.getTotalPages());
            model.addAttribute("currentPage", page);
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

}
