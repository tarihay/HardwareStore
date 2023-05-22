package ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated.OrderItemsEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.CartEntity;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Product")
@EqualsAndHashCode(exclude = "carts")
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Cart_Product",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "id")
    )
    private Set<CartEntity> carts;

}
