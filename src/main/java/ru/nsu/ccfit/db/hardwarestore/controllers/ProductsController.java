package ru.nsu.ccfit.db.hardwarestore.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.productRelated.ProductDTO;
import ru.nsu.ccfit.db.hardwarestore.services.ProductService;

import java.util.Set;

@Controller
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductsController {

    private ProductService productService;

    @GetMapping("/{productType}")
    public String getProductsByType(
            @PathVariable String productType,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "1") int size,
            Model model
    ) {
        Set<ProductDTO> products = productService.getProductsByType(productType);
        model.addAttribute("products", products);
        return "products";
    }
}
