package ru.nsu.ccfit.db.hardwarestore.model.dtos.orderRelated;

import ru.nsu.ccfit.db.hardwarestore.model.dtos.productRelated.ProductDTO;

public class OrderItemsDTO {
    private ProductDTO product;
    private OrderDetailsDTO orderDetails;

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public OrderDetailsDTO getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetailsDTO orderDetails) {
        this.orderDetails = orderDetails;
    }
}
