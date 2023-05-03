package ru.nsu.ccfit.db.hardwarestore.model.dtos.productRelated;

import java.util.HashSet;
import java.util.Set;

public class ProductTypeDTO {
    private String name;

    private Set<ProductFieldsDTO> productFields = new HashSet<>();

    private Set<ProductDTO> products = new HashSet<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProductFieldsDTO> getProductFields() {
        return productFields;
    }

    public void setProductFields(Set<ProductFieldsDTO> productFields) {
        this.productFields = productFields;
    }

    public Set<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDTO> products) {
        this.products = products;
    }
}
