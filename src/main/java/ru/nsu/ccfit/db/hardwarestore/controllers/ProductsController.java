package ru.nsu.ccfit.db.hardwarestore.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.db.hardwarestore.exceptions.ProductRelatedException;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.productRelated.ProductDTO;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.productRelated.ProductValueDTO;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.UserEntity;
import ru.nsu.ccfit.db.hardwarestore.services.ProductService;
import ru.nsu.ccfit.db.hardwarestore.utils.SecurityUtils;

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
        UserEntity user = new UserEntity();
        String username = SecurityUtils.getSessionUser();
        Set<ProductDTO> products = productService.getProductsByType(productType);
        model.addAttribute("products", products);
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
