package ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated;

import jakarta.persistence.*;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.UserEntity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Order_Details")
public class OrderDetailsEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long total;

    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "id")
    private UserEntity userId;

    @OneToMany(mappedBy = "orderDetails")
    private Set<OrderItemsEntity> orderItems = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_details_id", referencedColumnName = "id")
    private PaymentDetailsEntity paymentDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    public Set<OrderItemsEntity> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItemsEntity> orderItems) {
        this.orderItems = orderItems;
    }

    public PaymentDetailsEntity getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetailsEntity paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
}
