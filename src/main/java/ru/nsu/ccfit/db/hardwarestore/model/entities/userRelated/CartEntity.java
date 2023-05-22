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

    @ManyToMany(mappedBy = "carts", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ProductEntity> products = new HashSet<>();

    public CartEntity(UserEntity owner) {
        this.owner = owner;
    }

    public CartEntity() {

    }
}
