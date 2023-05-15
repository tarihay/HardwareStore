package ru.nsu.ccfit.db.hardwarestore.mappers.productRelated;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.productRelated.ProductDTO;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductEntity;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProductMapper {

    public ProductDTO mapToDTO(ProductEntity product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setSerialNumber(product.getSerialNumber());
        productDTO.setAmount(product.getAmount());
        productDTO.setManufacturer(product.getManufacturer());
        productDTO.setProductType(product.getProductType().getName());

        return productDTO;
    }

    public ProductEntity mapToEntity(ProductDTO productDTO) {
        ProductEntity product = new ProductEntity();
        product.setManufacturer(productDTO.getManufacturer());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setAmount(productDTO.getAmount());
        product.setSerialNumber(productDTO.getSerialNumber());

        return product;
    }
}
