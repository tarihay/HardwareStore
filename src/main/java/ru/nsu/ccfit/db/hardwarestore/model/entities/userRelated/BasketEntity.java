package ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated;

import jakarta.persistence.*;
import lombok.Data;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductEntity;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Basket")
public class BasketEntity {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "basket")
    private UserEntity owner;

    @OneToMany(mappedBy = "basket")
    private Set<ProductEntity> products = new HashSet<>();

    public BasketEntity(UserEntity owner) {
        this.owner = owner;
    }

    public BasketEntity() {

    }
}
