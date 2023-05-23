package ru.nsu.ccfit.db.hardwarestore.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.CartEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.CartItemEntity;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
    @Query(value = "SELECT ci FROM CartItemEntity ci WHERE ci.cart.id = :cartId")
    Page<CartItemEntity> findByCartId(@Param("cartId") Long cartId, Pageable pageable);
}
