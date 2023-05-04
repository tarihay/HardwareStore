package ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated;

import jakarta.persistence.*;
import lombok.Data;
import ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated.PaymentDetailsEntity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Bank_Account")
public class BankAccountEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "money_amount")
    private BigDecimal moneyAmount;

    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private UserEntity owner;

    @OneToMany(mappedBy = "from")
    private Set<PaymentDetailsEntity> paymentDetailsFrom = new HashSet<>();

    @OneToMany(mappedBy = "to")
    private Set<PaymentDetailsEntity> paymentDetailsTo = new HashSet<>();

}
