package ru.nsu.ccfit.db.hardwarestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.BasketEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.UserEntity;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<BasketEntity, Long> {
    Optional<BasketEntity> findByOwner(UserEntity owner);
}
