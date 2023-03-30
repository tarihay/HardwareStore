package ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated;

import jakarta.persistence.*;
import ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated.PaymentDetailsEntity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Bank_Account")
public class BankAccountEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "money_amount")
    private BigDecimal moneyAmount;

    @ManyToOne
    @JoinColumn(name = "id")
    private UserEntity owner;

    @OneToMany(mappedBy = "from")
    private Set<PaymentDetailsEntity> paymentDetailsFrom = new HashSet<>();

    @OneToMany(mappedBy = "to")
    private Set<PaymentDetailsEntity> paymentDetailsTo = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public Set<PaymentDetailsEntity> getPaymentDetailsFrom() {
        return paymentDetailsFrom;
    }

    public void setPaymentDetailsFrom(Set<PaymentDetailsEntity> paymentDetailsFrom) {
        this.paymentDetailsFrom = paymentDetailsFrom;
    }

    public Set<PaymentDetailsEntity> getPaymentDetailsTo() {
        return paymentDetailsTo;
    }

    public void setPaymentDetailsTo(Set<PaymentDetailsEntity> paymentDetailsTo) {
        this.paymentDetailsTo = paymentDetailsTo;
    }
}
