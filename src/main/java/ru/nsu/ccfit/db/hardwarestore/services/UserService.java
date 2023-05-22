package ru.nsu.ccfit.db.hardwarestore.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.db.hardwarestore.mappers.userRelated.UserMapper;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.userRelated.UserDTO;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.CartEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.RoleEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.UserEntity;
import ru.nsu.ccfit.db.hardwarestore.repositories.CartRepository;
import ru.nsu.ccfit.db.hardwarestore.repositories.ProductRepository;
import ru.nsu.ccfit.db.hardwarestore.repositories.RoleRepository;
import ru.nsu.ccfit.db.hardwarestore.repositories.UserRepository;

import java.util.Collections;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {
    private CartRepository cartRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserMapper userMapper;
    private ProductRepository productRepository;

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
        CartEntity cart = new CartEntity();
        user = userRepository.save(user);
        cart.setOwner(user);
        user.setCart(cart);
        cartRepository.save(cart);
    }

    public void updateUser(UserEntity user) {
        userRepository.save(user);
    }

    @Transactional
    public ProductEntity addProductToUsersCart(String email, Long productId) {
        UserEntity owner = userRepository.findByEmail(email).orElse(null);
        CartEntity usersCart = cartRepository.findByOwner(owner).orElse(null);
        if (usersCart != null) {
            Set<ProductEntity> cartProducts = usersCart.getProducts();
            ProductEntity product = productRepository.findById(productId).orElse(null);
            if (product != null) {
                cartProducts.add(product);
                product.getCarts().add(usersCart);
                usersCart.setProducts(cartProducts);

                cartRepository.save(usersCart);
                productRepository.save(product);

                return product;
            }
        }
        return null;
    }
}
