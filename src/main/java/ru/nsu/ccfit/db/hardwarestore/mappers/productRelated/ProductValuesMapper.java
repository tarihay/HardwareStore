package ru.nsu.ccfit.db.hardwarestore.mappers.productRelated;

import org.springframework.stereotype.Component;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.productRelated.ProductValuesDTO;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductValuesEntity;

@Component
public class ProductValuesMapper {
    //TODO сделать логику маппинга DTO в сущность

    public ProductValuesDTO mapToDTO(ProductValuesEntity productValues) {
        ProductValuesDTO productValuesDTO = new ProductValuesDTO();
        productValuesDTO.setValue(productValues.getValue());
        return productValuesDTO;
    }
//
//    public ProductValuesEntity mapToEntity(ProductValuesDTO productValuesDTO) {
//        ProductValuesEntity productValues = new ProductValuesEntity();
//        productValues.setValue(productValuesDTO.getValue());
//    }
}
