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
@Table(name = "Product_Fields")
public class ProductFieldEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "product-type", referencedColumnName = "name")
    private ProductTypeEntity productType;

    @OneToMany(mappedBy = "productField")
    private Set<ProductValueEntity> productValues = new HashSet<>();

}
