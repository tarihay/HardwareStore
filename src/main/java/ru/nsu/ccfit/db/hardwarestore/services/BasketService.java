package ru.nsu.ccfit.db.hardwarestore.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.db.hardwarestore.exceptions.NoSuchUserFoundException;
import ru.nsu.ccfit.db.hardwarestore.mappers.productRelated.ProductMapper;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.productRelated.ProductDTO;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.userRelated.BasketDTO;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.BasketEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.UserEntity;
import ru.nsu.ccfit.db.hardwarestore.repositories.BasketRepository;
import ru.nsu.ccfit.db.hardwarestore.repositories.UserRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BasketService {
    private UserRepository userRepository;
    private BasketRepository basketRepository;
    private ProductMapper productMapper;

    public BasketEntity createBasket(UserEntity user) {
        BasketEntity basket = new BasketEntity(user);
        return basketRepository.save(basket);
    }

    public BasketDTO getBasketByOwner(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchUserFoundException("No user found for this basket"));
        Optional<BasketEntity> basket = basketRepository.findByOwner(user);
        if (basket.isEmpty()) {
            basket = Optional.of(basketRepository.save(new BasketEntity(user)));
        }

        Set<ProductEntity> products = basket.get().getProducts();
        Set<ProductDTO> productDTOS = products.stream().map(product -> productMapper.mapToDTO(product)).collect(Collectors.toSet());

        return new BasketDTO(productDTOS);
    }
}
