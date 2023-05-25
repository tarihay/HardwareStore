package ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductEntity;

@Getter
@Setter
@Entity
@Table(name = "Cart_Product")
@EqualsAndHashCode(exclude = "cartItems")
public class CartItemEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private CartEntity cart;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity product;

    public CartItemEntity(CartEntity cart, ProductEntity product) {
        this.cart = cart;
        this.product = product;
    }

    public CartItemEntity() {

    }
}
