package ru.nsu.ccfit.db.hardwarestore.model.entities.userRelated;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Roles")
public class RoleEntity {
    @Id
    private String name;

    @ManyToMany(
        mappedBy = "roles"
    )
    private Set<UserEntity> users = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }
}
