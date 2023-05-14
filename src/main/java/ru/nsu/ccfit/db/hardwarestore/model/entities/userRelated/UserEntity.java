package ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated;

import jakarta.persistence.*;
import lombok.Data;
import ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated.RoleEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Users")
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private LocalDate bornDate;

    @ManyToMany
    @JoinTable(
            name = "User_Roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_name", referencedColumnName = "name")
    )
    private Set<RoleEntity> roles = new HashSet<>();

    @OneToOne(mappedBy = "owner")
    private BankAccountEntity bankAccount;

    public UserEntity(String username, String password, String firstname, String lastname, LocalDate bornDate) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.bornDate = bornDate;
    }

    public UserEntity() {

    }
}
