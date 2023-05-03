package ru.nsu.ccfit.db.hardwarestore.mappers.productRelated;

import org.springframework.stereotype.Component;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.productRelated.ProductDTO;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductEntity;

@Component
public class ProductMapper {
    public ProductDTO mapToDTO(ProductEntity product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setSerialNumber(product.getSerialNumber());
        productDTO.setAmount(product.getAmount());
        productDTO.setManufacturer(product.getManufacturer());
        //TODO сделать по-нормальному
//        productDTO.setOrderItems(product.getOrderItems());
//        productDTO.setProductValues(product.getProductValues());

        return productDTO;
    }
}
