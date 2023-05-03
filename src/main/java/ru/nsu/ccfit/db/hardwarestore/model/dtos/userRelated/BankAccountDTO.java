package ru.nsu.ccfit.db.hardwarestore.model.dtos.userRelated;

import ru.nsu.ccfit.db.hardwarestore.model.dtos.orderRelated.PaymentDetailsDTO;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class BankAccountDTO {


    private BigDecimal moneyAmount;

    private UserDTO owner;

    private Set<PaymentDetailsDTO> paymentDetailsFrom = new HashSet<>();

    private Set<PaymentDetailsDTO> paymentDetailsTo = new HashSet<>();

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }

    public Set<PaymentDetailsDTO> getPaymentDetailsFrom() {
        return paymentDetailsFrom;
    }

    public void setPaymentDetailsFrom(Set<PaymentDetailsDTO> paymentDetailsFrom) {
        this.paymentDetailsFrom = paymentDetailsFrom;
    }

    public Set<PaymentDetailsDTO> getPaymentDetailsTo() {
        return paymentDetailsTo;
    }

    public void setPaymentDetailsTo(Set<PaymentDetailsDTO> paymentDetailsTo) {
        this.paymentDetailsTo = paymentDetailsTo;
    }
}
