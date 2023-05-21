package ru.nsu.ccfit.db.hardwarestore.model.dtos.userRelated;

import lombok.Data;

@Data
public class LoginDTO {
    private String username;
    private String password;
}
