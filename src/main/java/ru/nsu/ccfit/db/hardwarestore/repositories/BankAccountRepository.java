package ru.nsu.ccfit.db.hardwarestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.BankAccountEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.UserEntity;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Long> {
    Optional<BankAccountEntity> findByOwner(UserEntity user);
}
