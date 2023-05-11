package ru.nsu.ccfit.db.hardwarestore.mappers.productRelated;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.productRelated.ProductDTO;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.productRelated.ProductValuesDTO;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductValuesEntity;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProductMapper {

    private ProductValuesMapper productValuesMapper;

    public ProductDTO mapToDTO(ProductEntity product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setSerialNumber(product.getSerialNumber());
        productDTO.setAmount(product.getAmount());
        productDTO.setManufacturer(product.getManufacturer());
        productDTO.setProductType(product.getProductType().getName());

        Set<ProductValuesDTO> productValuesDTOs =
                product.getProductValues()
                        .stream()
                        .map(productValue -> productValuesMapper.mapToDTO(productValue))
                        .collect(Collectors.toSet());
        productDTO.setProductValues(productValuesDTOs);

        return productDTO;
    }
}
