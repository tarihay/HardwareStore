package ru.nsu.ccfit.db.hardwarestore.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.CartEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductTypeEntity;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Page<ProductEntity> findAllByProductType(ProductTypeEntity productType, Pageable pageable);

    @Query(value = "SELECT p FROM ProductEntity p JOIN p.carts c WHERE c.id = :cartId")
    Page<ProductEntity> findByCartId(@Param("cartId") Long cartId, Pageable pageable);
}
