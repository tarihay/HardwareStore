package ru.nsu.ccfit.db.hardwarestore.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated.OrderItemEntity;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItemEntity, Long> {
    @Query(value = "SELECT oi FROM OrderItemEntity oi WHERE oi.orderDetails.id = :orderDetailsId")
    Page<OrderItemEntity> findByOrderDetails
            (@Param("orderDetailsId") Long orderDetailsId, Pageable pageable);
}
