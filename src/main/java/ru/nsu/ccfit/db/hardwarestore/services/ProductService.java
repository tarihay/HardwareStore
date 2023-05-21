package ru.nsu.ccfit.db.hardwarestore.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.db.hardwarestore.mappers.productRelated.ProductMapper;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.productRelated.ProductDTO;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.productRelated.ProductFieldsDTO;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.productRelated.ProductValueDTO;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductFieldEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductTypeEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductValueEntity;
import ru.nsu.ccfit.db.hardwarestore.repositories.ProductFieldsRepository;
import ru.nsu.ccfit.db.hardwarestore.repositories.ProductRepository;
import ru.nsu.ccfit.db.hardwarestore.repositories.ProductTypeRepository;
import ru.nsu.ccfit.db.hardwarestore.repositories.ProductValueRepository;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;
    private ProductMapper productMapper;
    private ProductTypeRepository productTypeRepository;
    private ProductFieldsRepository productFieldsRepository;
    private ProductValueRepository productValueRepository;

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

    public Long addProduct(ProductDTO productDTO) {
        ProductTypeEntity productType = getProductType(productDTO.getProductType());
        if (productType == null) {
            return null;
        }

        ProductEntity product = productMapper.mapToEntity(productDTO);
        product.setProductType(productType);
        ProductEntity savedProduct = productRepository.save(product);

        Optional<Set<ProductFieldEntity>> fieldsByTypeOptional = productFieldsRepository.findByProductType(productType);
        if (fieldsByTypeOptional.isEmpty()) {
            return null;
        }

        Set<ProductFieldEntity> fieldsByType = fieldsByTypeOptional.get();
        Set<ProductFieldsDTO> productDTOFields = productDTO.getProductFields();
        for (ProductFieldEntity field : fieldsByType) {
            for (ProductFieldsDTO dto : productDTOFields) {
                if (Objects.equals(field.getName(), dto.getName())) {
                    ProductValueEntity value = new ProductValueEntity();
                    value.setProduct(savedProduct);
                    value.setProductField(field);
                    value.setValue(dto.getValue());

                    productValueRepository.save(value);
                }
            }
        }

        return savedProduct.getId();
    }

    public ProductTypeEntity getProductType(String name) {
        Optional<ProductTypeEntity> productType = productTypeRepository.findByName(name);
        return productType.orElse(null);
    }

    public void saveProductType(ProductValueDTO typeDTO) {
        ProductTypeEntity type = new ProductTypeEntity();
        type.setName(typeDTO.getName());
        productTypeRepository.save(type);
    }
}
