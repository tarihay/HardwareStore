package ru.nsu.ccfit.db.hardwarestore.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.BankAccountEntity;

import java.math.BigDecimal;

@Slf4j
@Service
public class BankAccountService {
    @Transactional
    public void transferFunds(BankAccountEntity fromAccount, BankAccountEntity toAccount, BigDecimal amount) {
        BigDecimal fromAccountBalance = fromAccount.getMoneyAmount();
        BigDecimal toAccountBalance = toAccount.getMoneyAmount();

        if (fromAccountBalance.compareTo(amount) < 0) {
            log.error("Not enough money");
            return;
        }

        fromAccount.setMoneyAmount(fromAccountBalance.subtract(amount));
        toAccount.setMoneyAmount(toAccountBalance.add(amount));
    }
}
