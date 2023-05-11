package ru.nsu.ccfit.db.hardwarestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated.OrderDetailsEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderDetailsEntity, Long> {
}
