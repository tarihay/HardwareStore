package ru.nsu.ccfit.db.hardwarestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductTypeEntity;

import java.util.Optional;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, Long> {
    Optional<ProductTypeEntity> findByName(String name);
}
