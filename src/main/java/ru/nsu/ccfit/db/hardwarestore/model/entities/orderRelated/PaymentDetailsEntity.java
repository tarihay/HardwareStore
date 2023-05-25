package ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.BankAccountEntity;

@Getter
@Setter
@Entity
@Table(name = "Payment_Details")
public class PaymentDetailsEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "acc_from", referencedColumnName = "id")
    private BankAccountEntity from;

    @ManyToOne
    @JoinColumn(name = "acc_to", referencedColumnName = "id")
    private BankAccountEntity to;

    @OneToOne(mappedBy = "paymentDetails")
    private OrderDetailsEntity orderDetails;

    @ManyToOne
    @JoinColumn(name = "payment_status", referencedColumnName = "name")
    private PaymentStatusEntity paymentStatus;

}
