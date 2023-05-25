package ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Product_Types")
public class ProductTypeEntity {

    @Id
    private String name;

    @OneToMany(mappedBy = "productType")
    private Set<ProductFieldEntity> productFields = new HashSet<>();

    @OneToMany(mappedBy = "productType")
    private Set<ProductEntity> products = new HashSet<>();

}
