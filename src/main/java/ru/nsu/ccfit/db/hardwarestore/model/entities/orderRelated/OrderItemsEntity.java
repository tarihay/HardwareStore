package ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated;

import jakarta.annotation.sql.DataSourceDefinitions;
import jakarta.persistence.*;
import lombok.Data;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductEntity;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Order_Items")
public class OrderItemsEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "order-details", referencedColumnName = "id")
    private OrderDetailsEntity orderDetails;

}
