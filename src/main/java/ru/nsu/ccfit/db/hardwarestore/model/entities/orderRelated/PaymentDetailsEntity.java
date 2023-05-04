package ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated;

import jakarta.persistence.*;
import lombok.Data;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.BankAccountEntity;

@Data
@Entity
@Table(name = "Payment_Details")
public class PaymentDetailsEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "acc-from", referencedColumnName = "id")
    private BankAccountEntity from;

    @ManyToOne
    @JoinColumn(name = "acc-to", referencedColumnName = "id")
    private BankAccountEntity to;

    @OneToOne(mappedBy = "paymentDetails")
    private OrderDetailsEntity orderDetails;

    @ManyToOne
    @JoinColumn(name = "payment-status", referencedColumnName = "name")
    private PaymentStatusEntity paymentStatus;

}
