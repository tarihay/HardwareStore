package ru.nsu.ccfit.db.hardwarestore.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.db.hardwarestore.exceptions.NoSuchUserFoundException;
import ru.nsu.ccfit.db.hardwarestore.mappers.productRelated.ProductMapper;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.productRelated.ProductDTO;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.userRelated.CartDTO;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.CartEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.CartItemEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.UserEntity;
import ru.nsu.ccfit.db.hardwarestore.repositories.CartItemRepository;
import ru.nsu.ccfit.db.hardwarestore.repositories.CartRepository;
import ru.nsu.ccfit.db.hardwarestore.repositories.ProductRepository;
import ru.nsu.ccfit.db.hardwarestore.repositories.UserRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CartService {
    private UserRepository userRepository;
    private CartRepository cartRepository;
    private ProductMapper productMapper;
    private ProductRepository productRepository;
    private CartItemRepository cartItemRepository;

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
        return getProductsByCartItems(cart, pageable);
    }

    private Page<ProductDTO> getProductsByCartItems(CartEntity cart, Pageable pageable) {
        Page<CartItemEntity> cartItemPage  = cartItemRepository.findByCartId(cart.getId(), pageable);
        return cartItemPage.map(cartItem -> productMapper.mapToDTO(cartItem.getProduct()));
    }

    @Transactional
    public void removeItemFromUsersCart(String email, Long productId) {
        UserEntity owner = userRepository.findByEmail(email).orElse(null);
        CartEntity usersCart = cartRepository.findByOwner(owner).orElse(null);

        if (usersCart != null) {
            Set<CartItemEntity> cartItems = usersCart.getCartItems();
            CartItemEntity itemToRemove = null;
            for (CartItemEntity cartItem : cartItems) {
                if (cartItem.getProduct().getId().equals(productId)) {
                    itemToRemove = cartItem;
                    break;
                }
            }

            if (itemToRemove != null) {
                cartItems.remove(itemToRemove);
                usersCart.setCartItems(cartItems);
                cartItemRepository.delete(itemToRemove);
                cartRepository.save(usersCart);
            }
        }
    }

    @Transactional
    public void addProductToUsersCart(String email, Long productId) {
        UserEntity owner = userRepository.findByEmail(email).orElse(null);
        if (owner == null) {
            log.error("User {} not found", email);
            return;
        }
        CartEntity usersCart = cartRepository.findByOwner(owner).orElse(null);
        if (usersCart == null) {
            log.error("User's  {} cart not found", email);
            return;
        }
        ProductEntity product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            log.error("Product {} not found", productId);
            return;
        }

        CartItemEntity cartItem = new CartItemEntity(usersCart, product);
        cartItemRepository.save(cartItem);

        usersCart.getCartItems().add(cartItem);
        product.getCartItems().add(cartItem);

        cartRepository.save(usersCart);
        productRepository.save(product);
    }
}
