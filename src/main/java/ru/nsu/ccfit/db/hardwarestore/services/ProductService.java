package ru.nsu.ccfit.db.hardwarestore.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.db.hardwarestore.mappers.productRelated.ProductMapper;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.productRelated.ProductDTO;
import ru.nsu.ccfit.db.hardwarestore.repositories.ProductRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;
    private ProductMapper productMapper;

    public Set<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map((product) -> productMapper.mapToDTO(product))
                .collect(Collectors.toSet());
    }
}
