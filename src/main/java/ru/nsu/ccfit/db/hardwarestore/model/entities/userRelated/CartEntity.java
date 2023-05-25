package ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductEntity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Cart")
@EqualsAndHashCode(exclude = "carts")
public class CartEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "int default 0")
    private BigDecimal totalPrice = BigDecimal.ZERO;

    @OneToOne(mappedBy = "cart")
    private UserEntity owner;

    @OneToMany(mappedBy = "cart")
    @JsonIgnore
    private Set<CartItemEntity> cartItems;

    public CartEntity(UserEntity owner) {
        this.owner = owner;
    }

    public CartEntity() {

    }

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
        CartEntity other = (CartEntity) obj;
        if (!id.equals(other.id))
            return false;
        return true;
    }

    public void addToTotalPrice(double price) {
        totalPrice = totalPrice.add(BigDecimal.valueOf(price));
    }

    public void decreaseTotalPrice(double price) {
        if (totalPrice.compareTo(BigDecimal.valueOf(price)) < 0) {
            totalPrice = BigDecimal.ZERO;
        } else {
            totalPrice = totalPrice.subtract(BigDecimal.valueOf(price));
        }
    }
}
