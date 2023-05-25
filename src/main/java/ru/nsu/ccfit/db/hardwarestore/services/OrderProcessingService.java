package ru.nsu.ccfit.db.hardwarestore.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated.OrderDetailsEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated.OrderItemEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.BankAccountEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.UserEntity;
import ru.nsu.ccfit.db.hardwarestore.repositories.BankAccountRepository;
import ru.nsu.ccfit.db.hardwarestore.repositories.OrderRepository;
import ru.nsu.ccfit.db.hardwarestore.repositories.PaymentStatusRepository;
import ru.nsu.ccfit.db.hardwarestore.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class OrderProcessingService {

    private final OrderRepository orderRepository;
    private final BankAccountRepository bankAccountRepository;
    private final ProductRepository productRepository;
    private final PaymentStatusRepository paymentStatusRepository;
    private final BankAccountService bankAccountService;
    private final ProductService productService;

    @Scheduled(fixedDelay = 10000)
    @Transactional
    public void processNewOrders() {
        List<OrderDetailsEntity> newOrderDetails = orderRepository.findByPaymentDetailsPaymentStatusName("NEW");
        for (OrderDetailsEntity orderDetails : newOrderDetails) {
            UserEntity user = orderDetails.getOwner();
            BigDecimal totalCost = orderDetails.getTotal();
            BigDecimal userMoneyAmount = user.getBankAccount().getMoneyAmount();

            if (userMoneyAmount.compareTo(totalCost) < 0) {
                orderDetails.getPaymentDetails().setPaymentStatus(paymentStatusRepository.findByName("REJECTED").orElse(null));
                continue;
            }

            Map<Long, Integer> productQuantities = new HashMap<>();
            for (OrderItemEntity orderItem : orderDetails.getOrderItems()) {
                Long productId = orderItem.getProduct().getId();
                productQuantities.put(productId, productQuantities.getOrDefault(productId, 0) + 1);
            }

            boolean allProductsAvailable = true;
            for (Map.Entry<Long, Integer> entry : productQuantities.entrySet()) {
                Long productId = entry.getKey();
                int requiredQuantity = entry.getValue();
                ProductEntity product = productRepository.findById(productId).orElse(null);
                long productAmount = product.getAmount();

                if (productAmount < requiredQuantity) {
                    allProductsAvailable = false;
                    break;
                }
            }

            if (allProductsAvailable) {
                for (Map.Entry<Long, Integer> entry : productQuantities.entrySet()) {
                    Long productId = entry.getKey();
                    int requiredQuantity = entry.getValue();
                    ProductEntity product = productRepository.findById(productId).orElse(null);
                    productService.decreaseProductAmount(product, requiredQuantity);
                }
                BankAccountEntity shopOwnerBankAccount = bankAccountRepository.findById(1L).orElse(null);
                bankAccountService.transferFunds(user.getBankAccount(), shopOwnerBankAccount, totalCost);
                orderDetails.getPaymentDetails().setPaymentStatus(paymentStatusRepository.findByName("ACCEPTED").orElse(null));
            } else {
                orderDetails.getPaymentDetails().setPaymentStatus(paymentStatusRepository.findByName("REJECTED").orElse(null));
            }
        }
    }
}
