package ru.nsu.ccfit.db.hardwarestore.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated.OrderDetailsEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated.OrderItemEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated.PaymentDetailsEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated.PaymentStatusEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.BankAccountEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.UserEntity;
import ru.nsu.ccfit.db.hardwarestore.repositories.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private OrderItemsRepository orderItemsRepository;
    private CartService cartService;
    private UserService userService;
    private BankAccountRepository bankAccountRepository;
    private PaymentStatusRepository paymentStatusRepository;
    private UserRepository userRepository;
    private PaymentDetailsRepository paymentDetailsRepository;


    @Transactional
    public String createOrder(String email) {
        UserEntity user = userService.getUserByEmail(email);
        BankAccountEntity bankAccount = user.getBankAccount();
        List<ProductEntity> cartItems = cartService.getItemsFromUsersCart(email);
        if (cartItems.isEmpty()) {
            return "no-items";
        }

        BigDecimal totalCost = cartService.getTotalPrice(email);
        BigDecimal userMoneyAmount = bankAccount.getMoneyAmount();

        if (userMoneyAmount.compareTo(totalCost) < 0) {
            return "money";
        }

        PaymentStatusEntity newPaymentStatus = paymentStatusRepository.findByName("NEW").get();

        OrderDetailsEntity orderDetails = new OrderDetailsEntity();
        orderDetails.setTotal(totalCost);
        orderDetails.setOwner(user);
        orderDetails.setCreatedAt(LocalDate.now());
        Long orderId = orderRepository.save(orderDetails).getId();

        for (ProductEntity product : cartItems) {
            OrderItemEntity orderItem = new OrderItemEntity();
            orderItem.setProduct(product);
            orderItem.setOrderDetails(orderDetails);
            orderItemsRepository.save(orderItem);
        }

        PaymentDetailsEntity paymentDetails = new PaymentDetailsEntity();
        paymentDetails.setFrom(bankAccount);
        bankAccount.decreaseTotalPrice(totalCost);
        paymentDetails.setTo(bankAccountRepository.findById(1L).orElse(null));
        paymentDetails.setPaymentStatus(newPaymentStatus);
        paymentDetails.setOrderDetails(orderDetails);
        paymentDetails = paymentDetailsRepository.save(paymentDetails);

        orderDetails.setPaymentDetails(paymentDetails);

        cartService.clearUserCart(email);

        return orderId.toString();
    }

    public Page<OrderDetailsEntity> getUserOrders(String email, Pageable pageable) {
        UserEntity user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            log.error("User with email {} not found", email);
            return null;
        }

        return orderRepository.findByOwner(user.getId(), pageable);
    }

    public Page<ProductEntity> getOrderById(Long orderDetailsId, Pageable pageable) {
        OrderDetailsEntity orderDetailsEntity = orderRepository.findById(orderDetailsId).orElse(null);

        if (orderDetailsEntity == null) {
            throw new RuntimeException("OrderDetails with id " + orderDetailsId + " not found");
        }

        List<ProductEntity> products = orderDetailsEntity.getOrderItems()
                .stream()
                .map(OrderItemEntity::getProduct)
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), products.size());

        return new PageImpl<>(products.subList(start, end), pageable, products.size());
    }
}
