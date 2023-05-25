package ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.nsu.ccfit.db.hardwarestore.model.entities.orderRelated.OrderDetailsEntity;
import ru.nsu.ccfit.db.hardwarestore.model.entities.productRelated.ProductEntity;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column (unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String firstname;

    @Column
    private String lastname;

    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "User_Roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_name", referencedColumnName = "name")
    )
    private Set<RoleEntity> roles = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "bank_account_id", referencedColumnName = "id")
    private BankAccountEntity bankAccount;

    @OneToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private CartEntity cart;

    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private Set<OrderDetailsEntity> orders = new HashSet<>();
}
