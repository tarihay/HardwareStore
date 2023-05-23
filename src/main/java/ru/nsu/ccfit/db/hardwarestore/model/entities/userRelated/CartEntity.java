package ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductEntity;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Cart")
@EqualsAndHashCode(exclude = "carts")
public class CartEntity {
    @Id
    @GeneratedValue
    private Long id;

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
}
