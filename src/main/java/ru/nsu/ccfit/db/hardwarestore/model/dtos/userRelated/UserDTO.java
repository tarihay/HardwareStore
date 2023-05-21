package ru.nsu.ccfit.db.hardwarestore.model.dtos.userRelated;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDTO {
    private String username;
    private String password;
    private String passwordCheck;
    private String firstname;
    private String lastname;
    private String email;

    private Set<String> roles = new HashSet<>();

    private Set<BankAccountDTO> bankAccounts = new HashSet<>();

}