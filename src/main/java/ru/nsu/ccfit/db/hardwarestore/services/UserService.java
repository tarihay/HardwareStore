package ru.nsu.ccfit.db.hardwarestore.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
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

    public void saveUser(UserDTO userDTO) {
        UserEntity user = userMapper.mapToEntity(userDTO);
        RoleEntity userRole = roleRepository.findByName("USER").orElse(null);
        user.setRoles(Collections.singleton(userRole));
        user = userRepository.save(user);

        CartEntity cart = new CartEntity();
        cart.setOwner(user);
        user.setCart(cart);
        cartRepository.save(cart);

        BankAccountEntity bankAccount = new BankAccountEntity();
        bankAccount.setOwner(user);
        bankAccount.setMoneyAmount(BigDecimal.ZERO);
        user.setBankAccount(bankAccount);
        bankAccountRepository.save(bankAccount);
    }

    public void updateUser(UserEntity user) {
        userRepository.save(user);
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
