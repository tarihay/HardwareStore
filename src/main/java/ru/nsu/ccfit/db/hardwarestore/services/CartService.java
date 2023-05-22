package ru.nsu.ccfit.db.hardwarestore.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.db.hardwarestore.exceptions.NoSuchUserFoundException;
import ru.nsu.ccfit.db.hardwarestore.mappers.productRelated.ProductMapper;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.productRelated.ProductDTO;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.userRelated.CartDTO;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.CartEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.UserEntity;
import ru.nsu.ccfit.db.hardwarestore.repositories.CartRepository;
import ru.nsu.ccfit.db.hardwarestore.repositories.UserRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartService {
    private UserRepository userRepository;
    private CartRepository cartRepository;
    private ProductMapper productMapper;
    private ProductService productService;

    public CartEntity createCart(UserEntity user) {
        CartEntity cart = new CartEntity(user);
        return cartRepository.save(cart);
    }

    public Page<ProductDTO> getCartByOwner(String username, Pageable pageable) {
        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new NoSuchUserFoundException("No user found for this cart"));
        Optional<CartEntity> cartOpt = cartRepository.findByOwner(user);
        if (cartOpt.isEmpty()) {
            cartOpt = Optional.of(cartRepository.save(new CartEntity(user)));
        }
        CartEntity cart = cartOpt.get();
        return productService.getProductsByCart(cart, pageable);
    }
}
