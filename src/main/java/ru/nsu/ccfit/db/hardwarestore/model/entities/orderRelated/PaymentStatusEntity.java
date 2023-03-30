package ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Payment_Status")
public class PaymentStatusEntity {

    @Id
    private String name;

    @OneToMany(mappedBy = "paymentStatus")
    private Set<PaymentDetailsEntity> paymentDetails = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PaymentDetailsEntity> getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(Set<PaymentDetailsEntity> paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
}
