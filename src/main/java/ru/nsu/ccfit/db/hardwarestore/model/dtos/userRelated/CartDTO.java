package ru.nsu.ccfit.db.hardwarestore.model.dtos.userRelated;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.productRelated.ProductDTO;

import java.util.Set;

@Data
@AllArgsConstructor
public class CartDTO {
    private Set<ProductDTO> products;
}
