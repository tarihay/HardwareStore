package ru.nsu.ccfit.db.hardwarestore.model.dtos.productRelated;

import lombok.Data;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.orderRelated.OrderItemsDTO;

import java.util.HashSet;
import java.util.Set;

@Data
public class ProductDTO {
    private String name;
    private String serialNumber;
    private String manufacturer;
    private Long price;
    private Long amount;
    private String productType;
    private Set<ProductFieldsDTO> productFields = new HashSet<>();
}
