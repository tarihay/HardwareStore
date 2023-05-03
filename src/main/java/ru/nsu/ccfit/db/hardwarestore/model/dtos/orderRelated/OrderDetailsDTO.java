package ru.nsu.ccfit.db.hardwarestore.model.dtos.orderRelated;

import ru.nsu.ccfit.db.hardwarestore.model.dtos.userRelated.UserDTO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class OrderDetailsDTO {

    private Long total;

    private Date createdAt;

    private UserDTO userId;

    private Set<OrderItemsDTO> orderItems = new HashSet<>();

    private PaymentDetailsDTO paymentDetails;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public UserDTO getUserId() {
        return userId;
    }

    public void setUserId(UserDTO userId) {
        this.userId = userId;
    }

    public Set<OrderItemsDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItemsDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public PaymentDetailsDTO getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetailsDTO paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
}
