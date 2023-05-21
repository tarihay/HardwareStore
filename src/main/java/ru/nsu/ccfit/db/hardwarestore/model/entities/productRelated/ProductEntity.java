package ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated;

import jakarta.persistence.*;
import lombok.Data;
import ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated.OrderItemsEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.BasketEntity;

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
    @JoinColumn(name = "product-type", referencedColumnName = "name")
    private ProductTypeEntity productType;

    @OneToMany(mappedBy = "product")
    private Set<ProductValueEntity> productValues = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<OrderItemsEntity> orderItems = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "basket_id", referencedColumnName = "id")
    private BasketEntity basket;

}
