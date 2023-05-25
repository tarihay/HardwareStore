package ru.nsu.ccfit.db.hardwarestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated.PaymentStatusEntity;

import java.util.Optional;

@Repository
public interface PaymentStatusRepository extends JpaRepository<PaymentStatusEntity, String> {
    Optional<PaymentStatusEntity> findByName(String name);
}
