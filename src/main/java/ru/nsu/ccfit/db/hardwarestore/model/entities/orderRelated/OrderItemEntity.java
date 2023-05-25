package ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductEntity;

@Getter
@Setter
@Entity
@Table(name = "Order_Items")
public class OrderItemEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "order_details", referencedColumnName = "id")
    private OrderDetailsEntity orderDetails;
}
