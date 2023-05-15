package ru.nsu.ccfit.db.hardwarestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductFieldEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductTypeEntity;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductFieldsRepository extends JpaRepository<ProductFieldEntity, Long> {
    Optional<Set<ProductFieldEntity>> findByProductType(ProductTypeEntity type);
}
