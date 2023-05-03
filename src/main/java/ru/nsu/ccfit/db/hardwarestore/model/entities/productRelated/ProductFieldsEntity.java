package ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

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

    public ProductTypeEntity getProductType() {
        return productType;
    }

    public void setProductType(ProductTypeEntity productType) {
        this.productType = productType;
    }

    public Set<ProductValuesEntity> getProductValues() {
        return productValues;
    }

    public void setProductValues(Set<ProductValuesEntity> productValues) {
        this.productValues = productValues;
    }
}
