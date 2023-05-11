package ru.nsu.ccfit.db.hardwarestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
