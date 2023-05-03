package ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated;

import jakarta.persistence.*;
import lombok.Data;
import ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated.OrderItemsEntity;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Product")
public class ProductEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String serialNumber;

    @Column
    private String manufacturer;

    @Column
    private Long price;

    @Column
    private Long amount;

    @ManyToOne
    @JoinColumn(name = "product-type", referencedColumnName = "id")
    private ProductTypeEntity productType;

    @OneToMany(mappedBy = "product")
    private Set<ProductValuesEntity> productValues = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<OrderItemsEntity> orderItems = new HashSet<>();

}
