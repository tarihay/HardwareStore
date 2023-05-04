package ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Product_Types")
public class ProductTypeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "productType")
    private Set<ProductFieldsEntity> productFields = new HashSet<>();

    @OneToMany(mappedBy = "productType")
    private Set<ProductEntity> products = new HashSet<>();

}
