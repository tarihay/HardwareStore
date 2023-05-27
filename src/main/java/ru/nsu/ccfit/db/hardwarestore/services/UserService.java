package ru.nsu.ccfit.db.hardwarestore.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.db.hardwarestore.exceptions.NoSuchUserFoundException;
import ru.nsu.ccfit.db.hardwarestore.mappers.userRelated.UserMapper;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.userRelated.UserDTO;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.*;
import ru.nsu.ccfit.db.hardwarestore.repositories.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {
    private CartRepository cartRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserMapper userMapper;
    private BankAccountRepository bankAccountRepository;

    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public UserDTO getUserDTOByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return null;
        }

        UserDTO dto = userMapper.mapToDTO(user);

        BankAccountEntity bankAccount = bankAccountRepository.findByOwner(user).orElse(null);
        if (bankAccount != null) {
            dto.setMoneyAmount(bankAccount.getMoneyAmount().doubleValue());
        } else {
            dto.setMoneyAmount(BigDecimal.ZERO.doubleValue());
        }

        return dto;
    }

    @Transactional
    public String saveUser(UserDTO userDTO) {
        UserEntity user = userMapper.mapToEntity(userDTO);
        RoleEntity userRole = roleRepository.findByName("USER").orElse(null);
        user.setRoles(Collections.singleton(userRole));

        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            log.error("Неверный формат email");
            return "email-error";
        }


        CartEntity cart = new CartEntity();
        cart.setOwner(user);
        user.setCart(cart);
        cartRepository.save(cart);

        BankAccountEntity bankAccount = new BankAccountEntity();
        bankAccount.setOwner(user);
        bankAccount.setMoneyAmount(BigDecimal.ZERO);
        bankAccountRepository.save(bankAccount);

        user.setBankAccount(bankAccount);

        return "good";
    }

    public String updateUser(UserEntity user) {
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            log.error("Неверный формат email");
            return "email-error";
        }

        return "good";
    }

    @Transactional
    public void addCash(String email, Double amount) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchUserFoundException("No user found with email " + email));

        BankAccountEntity bankAccount = userEntity.getBankAccount();
        if (bankAccount == null) {
            throw new IllegalStateException("User does not have a bank account");
        }

        BigDecimal updatedAmount = bankAccount.getMoneyAmount().add(BigDecimal.valueOf(amount));
        bankAccount.setMoneyAmount(updatedAmount);

        userRepository.save(userEntity);
    }

}
