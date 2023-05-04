package ru.nsu.ccfit.db.hardwarestore.model.dtos.userRelated;

import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDTO {

    private String username;

    private String password;

    private String firstname;

    private String lastname;

    private Date bornDate;

    private Set<String> roles = new HashSet<>();

    private Set<BankAccountDTO> bankAccounts = new HashSet<>();

}