package ru.nsu.ccfit.db.hardwarestore.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.db.hardwarestore.repositories.OrderRepository;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
}
