package ru.nsu.ccfit.db.hardwarestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated.PaymentDetailsEntity;

@Repository
public interface PaymentDetailsRepository extends JpaRepository<PaymentDetailsEntity, Long> {
}
