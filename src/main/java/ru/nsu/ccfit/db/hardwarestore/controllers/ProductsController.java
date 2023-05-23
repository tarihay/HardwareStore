package ru.nsu.ccfit.db.hardwarestore.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.nsu.ccfit.db.hardwarestore.exceptions.ProductRelatedException;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.productRelated.ProductDTO;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.productRelated.ProductValueDTO;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.UserEntity;
import ru.nsu.ccfit.db.hardwarestore.services.CartService;
import ru.nsu.ccfit.db.hardwarestore.services.ProductService;
import ru.nsu.ccfit.db.hardwarestore.services.UserService;
import ru.nsu.ccfit.db.hardwarestore.utils.SecurityUtils;

import java.util.Set;

@Controller
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductsController {
    private UserService userService;
    private CartService cartService;
    private ProductService productService;

    @PostMapping("/add-to-cart/{productId}")
    public String addProductToCart(
            @PathVariable long productId,
            HttpServletRequest request
    ) {
        String email = SecurityUtils.getSessionUser();
        cartService.addProductToUsersCart(email, productId);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }


    @GetMapping("/{productType}")
    public String getProductsByType(
            @PathVariable String productType,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "7") int size,
            Model model
    ) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ProductDTO> products = productService.getProductsByType(productType, pageable);
        model.addAttribute("products", products.getContent());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        return "products";
    }

    @PostMapping("/add-product")
    public ResponseEntity<Long> addProduct(@RequestBody ProductDTO product) {
        Long id = productService.addProduct(product);
        if (id == null) {
            throw new ProductRelatedException("Error adding new product");
        }
        return ResponseEntity.ok().body(id);
    }

    @PostMapping("/add-type")
    public ResponseEntity<?> addProductType(@RequestBody ProductValueDTO type) {
        productService.saveProductType(type);
        return ResponseEntity.ok().body("Success");
    }


}
