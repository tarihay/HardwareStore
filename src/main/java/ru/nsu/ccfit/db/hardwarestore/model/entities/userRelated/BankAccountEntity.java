package ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated.PaymentDetailsEntity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Bank_Account")
public class BankAccountEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "money_amount")
    private BigDecimal moneyAmount;

    @OneToOne(mappedBy = "bankAccount")
    private UserEntity owner;

    @OneToMany(mappedBy = "from")
    private Set<PaymentDetailsEntity> paymentDetailsFrom = new HashSet<>();

    @OneToMany(mappedBy = "to")
    private Set<PaymentDetailsEntity> paymentDetailsTo = new HashSet<>();

    public void addToTotalPrice(BigDecimal price) {
        moneyAmount = moneyAmount.add(price);
    }

    public void decreaseTotalPrice(BigDecimal price) {
        if (moneyAmount.compareTo(price) < 0) {
            moneyAmount = BigDecimal.ZERO;
        } else {
            moneyAmount = moneyAmount.subtract(price);
        }
    }

}
