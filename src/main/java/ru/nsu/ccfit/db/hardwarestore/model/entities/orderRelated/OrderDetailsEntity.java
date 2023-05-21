package ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated;

import jakarta.persistence.*;
import lombok.Data;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.UserEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Order_Details")
public class OrderDetailsEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long total;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private UserEntity userId;

    @OneToMany(mappedBy = "orderDetails")
    private Set<OrderItemsEntity> orderItems = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_details_id", referencedColumnName = "id")
    private PaymentDetailsEntity paymentDetails;

}
