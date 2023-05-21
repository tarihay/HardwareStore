package ru.nsu.ccfit.db.hardwarestore.model.dtos.userRelated;

import lombok.Data;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.orderRelated.PaymentDetailsDTO;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class BankAccountDTO {


    private BigDecimal moneyAmount;

    private UserDTO owner;

    private Set<PaymentDetailsDTO> paymentDetailsFrom = new HashSet<>();

    private Set<PaymentDetailsDTO> paymentDetailsTo = new HashSet<>();

}
