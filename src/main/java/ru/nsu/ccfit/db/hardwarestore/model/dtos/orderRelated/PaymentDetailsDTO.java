package ru.nsu.ccfit.db.hardwarestore.model.dtos.orderRelated;

import lombok.Data;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.userRelated.BankAccountDTO;

@Data
public class PaymentDetailsDTO {
    private BankAccountDTO from;
    private BankAccountDTO to;
    private OrderDetailsDTO orderDetails;

    private String paymentStatus;

}
