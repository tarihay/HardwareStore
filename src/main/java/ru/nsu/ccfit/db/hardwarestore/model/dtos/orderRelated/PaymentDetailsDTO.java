package ru.nsu.ccfit.db.hardwarestore.model.dtos.orderRelated;

import ru.nsu.ccfit.db.hardwarestore.model.dtos.userRelated.BankAccountDTO;

public class PaymentDetailsDTO {
    private BankAccountDTO from;
    private BankAccountDTO to;
    private OrderDetailsDTO orderDetails;

    private String paymentStatus;

    public BankAccountDTO getFrom() {
        return from;
    }

    public void setFrom(BankAccountDTO from) {
        this.from = from;
    }

    public BankAccountDTO getTo() {
        return to;
    }

    public void setTo(BankAccountDTO to) {
        this.to = to;
    }

    public OrderDetailsDTO getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetailsDTO orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
