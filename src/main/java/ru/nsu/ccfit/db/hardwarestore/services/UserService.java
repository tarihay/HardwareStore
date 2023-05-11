package ru.nsu.ccfit.db.hardwarestore.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.db.hardwarestore.repositories.UserRepository;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

}
