package ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated;

import jakarta.persistence.*;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductEntity;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Order_Items")
public class OrderItemsEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "id")
    private OrderDetailsEntity orderDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public OrderDetailsEntity getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetailsEntity orderDetails) {
        this.orderDetails = orderDetails;
    }
}
