package ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Product_Values")
public class ProductValuesEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String value;

    @ManyToOne
    @JoinColumn(name = "product-fields", referencedColumnName = "id")
    private ProductFieldsEntity productFields;

    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "id")
    private ProductEntity product;

}
