package ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Payment_Status")
public class PaymentStatusEntity {

    @Id
    private String name;

    @OneToMany(mappedBy = "paymentStatus")
    private Set<PaymentDetailsEntity> paymentDetails = new HashSet<>();


}
