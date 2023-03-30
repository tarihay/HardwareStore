package ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProductFieldsEntity> getProductFields() {
        return productFields;
    }

    public void setProductFields(Set<ProductFieldsEntity> productFields) {
        this.productFields = productFields;
    }

    public Set<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductEntity> products) {
        this.products = products;
    }
}
