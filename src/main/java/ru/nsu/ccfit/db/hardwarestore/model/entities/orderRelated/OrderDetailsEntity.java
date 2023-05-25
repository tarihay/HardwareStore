package ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.UserEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Order_Details")
public class OrderDetailsEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private BigDecimal total;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "id")
    @JsonIgnore
    private UserEntity owner;

    @OneToMany(mappedBy = "orderDetails")
    private Set<OrderItemEntity> orderItems = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_details_id", referencedColumnName = "id")
    private PaymentDetailsEntity paymentDetails;

}
