package ru.nsu.ccfit.db.hardwarestore.mappers.userRelated;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.db.hardwarestore.model.dtos.userRelated.UserDTO;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.UserEntity;

@Component
@AllArgsConstructor
public class UserMapper {
    private PasswordEncoder passwordEncoder;

    public UserEntity mapToEntity(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return user;
    }

    public UserDTO mapToDTO(UserEntity user) {
        UserDTO dto = new UserDTO();
        dto.setFirstname(user.getFirstname());
        dto.setLastname(user.getLastname());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());

        return dto;
    }
}
