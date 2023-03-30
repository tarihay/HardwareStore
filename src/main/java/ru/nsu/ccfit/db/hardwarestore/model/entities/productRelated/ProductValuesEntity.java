package ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Product_Values")
public class ProductValuesEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String value;

    @ManyToOne
    @JoinColumn(name = "id")
    private ProductFieldsEntity productFields;

    @ManyToOne
    @JoinColumn(name = "id")
    private ProductEntity product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ProductFieldsEntity getProductFields() {
        return productFields;
    }

    public void setProductFields(ProductFieldsEntity productFields) {
        this.productFields = productFields;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}
