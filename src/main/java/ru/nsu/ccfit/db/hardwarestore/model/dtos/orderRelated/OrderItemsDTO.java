package ru.nsu.ccfit.db.hardwarestore.model.dtos.orderRelated;

import lombok.Data;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.productRelated.ProductDTO;

@Data
public class OrderItemsDTO {
    private ProductDTO product;
    private OrderDetailsDTO orderDetails;

}
