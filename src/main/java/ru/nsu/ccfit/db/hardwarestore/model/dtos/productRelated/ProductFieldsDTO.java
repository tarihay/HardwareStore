package ru.nsu.ccfit.db.hardwarestore.model.dtos.productRelated;

import java.util.HashSet;
import java.util.Set;

public class ProductFieldsDTO {

    //TODO сделать везде Data
    private String name;

    private Set<ProductValuesDTO> productValues = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProductValuesDTO> getProductValues() {
        return productValues;
    }

    public void setProductValues(Set<ProductValuesDTO> productValues) {
        this.productValues = productValues;
    }
}
