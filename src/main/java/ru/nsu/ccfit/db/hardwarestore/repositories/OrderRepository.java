package ru.nsu.ccfit.db.hardwarestore.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated.OrderDetailsEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.UserEntity;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderDetailsEntity, Long> {
    @Query(value = "SELECT od FROM OrderDetailsEntity od WHERE od.owner.id = :userId")
    Page<OrderDetailsEntity> findByOwner(@Param("userId") Long userId, Pageable pageable);

    List<OrderDetailsEntity> findByPaymentDetailsPaymentStatusName(String status);
}
