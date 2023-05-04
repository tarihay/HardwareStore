package ru.nsu.ccfit.db.hardwarestore.model.dtos.productRelated;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ProductTypeDTO {
    private String name;

    private Set<ProductFieldsDTO> productFields = new HashSet<>();

    private Set<ProductDTO> products = new HashSet<>();

}
