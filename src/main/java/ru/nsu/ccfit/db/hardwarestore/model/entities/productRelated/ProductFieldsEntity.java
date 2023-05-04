package ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Product_Fields")
public class ProductFieldsEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "product-type", referencedColumnName = "id")
    private ProductTypeEntity productType;

    @OneToMany(mappedBy = "productFields")
    private Set<ProductValuesEntity> productValues = new HashSet<>();

}
