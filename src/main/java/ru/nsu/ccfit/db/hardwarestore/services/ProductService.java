package ru.nsu.ccfit.db.hardwarestore.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.db.hardwarestore.mappers.productRelated.ProductMapper;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.productRelated.ProductDTO;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductTypeEntity;
import ru.nsu.ccfit.db.hardwarestore.repositories.ProductRepository;
import ru.nsu.ccfit.db.hardwarestore.repositories.ProductTypeRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;
    private ProductMapper productMapper;
    private ProductTypeRepository productTypeRepository;

    public Set<ProductDTO> getProductsByType(String productTypeName) {
        Optional<ProductTypeEntity> productTypeOptional = productTypeRepository.findByName(productTypeName);
        ProductTypeEntity productTypeEntity = null;
        if (productTypeOptional.isPresent()) {
            productTypeEntity = productTypeOptional.get();
        }
        else {
            return null;
        }

        return productRepository.findAllByProductType(productTypeEntity)
                .stream()
                .map((product) -> productMapper.mapToDTO(product))
                .collect(Collectors.toSet());
    }

    public ProductDTO getProductById(Long id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        return product.map(productEntity -> productMapper.mapToDTO(productEntity)).orElse(null);
    }
}
