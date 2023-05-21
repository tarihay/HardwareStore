package ru.nsu.ccfit.db.hardwarestore.model.dtos.orderRelated;

import lombok.Data;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.userRelated.UserDTO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class OrderDetailsDTO {

    private Long total;

    private Date createdAt;

    private UserDTO userId;

    private Set<OrderItemsDTO> orderItems = new HashSet<>();

    private PaymentDetailsDTO paymentDetails;

}
