package ru.nsu.ccfit.db.hardwarestore.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.db.hardwarestore.mappers.userRelated.UserMapper;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.userRelated.UserDTO;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.BasketEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.RoleEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.UserEntity;
import ru.nsu.ccfit.db.hardwarestore.repositories.BasketRepository;
import ru.nsu.ccfit.db.hardwarestore.repositories.RoleRepository;
import ru.nsu.ccfit.db.hardwarestore.repositories.UserRepository;

import java.util.Collections;

@Service
@AllArgsConstructor
public class UserService {
    private BasketRepository basketRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserMapper userMapper;

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

        return userMapper.mapToDTO(user);
    }

    public void saveUser(UserDTO userDTO) {
        UserEntity user = userMapper.mapToEntity(userDTO);
        RoleEntity userRole = roleRepository.findByName("USER").orElse(null);
        user.setRoles(Collections.singleton(userRole));
        BasketEntity basket = new BasketEntity();
        user = userRepository.save(user);
        basket.setOwner(user);
        user.setBasket(basket);
        basketRepository.save(basket);
    }

    public void updateUser(UserEntity user) {
        userRepository.save(user);
    }
}
