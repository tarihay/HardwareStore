package ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated;

import jakarta.persistence.*;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.BankAccountEntity;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BankAccountEntity getFrom() {
        return from;
    }

    public void setFrom(BankAccountEntity from) {
        this.from = from;
    }

    public BankAccountEntity getTo() {
        return to;
    }

    public void setTo(BankAccountEntity to) {
        this.to = to;
    }

    public OrderDetailsEntity getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetailsEntity orderDetails) {
        this.orderDetails = orderDetails;
    }

    public PaymentStatusEntity getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatusEntity paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
