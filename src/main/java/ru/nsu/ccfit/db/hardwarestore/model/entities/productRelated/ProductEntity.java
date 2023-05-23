package ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated.OrderItemsEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.CartEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.CartItemEntity;

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

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<CartItemEntity> cartItems;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProductEntity other = (ProductEntity) obj;
        if (!id.equals(other.id))
            return false;
        return true;
    }

}
