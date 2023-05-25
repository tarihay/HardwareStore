package ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Product_Values")
public class ProductValueEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String value;

    @ManyToOne
    @JoinColumn(name = "product-fields", referencedColumnName = "id")
    private ProductFieldEntity productField;

    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "id")
    private ProductEntity product;

}
