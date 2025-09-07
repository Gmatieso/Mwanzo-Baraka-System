package com.gmatieso.mwanzo.user.role.models;

import com.gmatieso.mwanzo.user.role.enums.RoleEnum;
import com.gmatieso.mwanzo.user.permission.models.Permission;
import com.gmatieso.mwanzo.user.user.entity.User;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {
    @Id
    @UuidGenerator
    private UUID id;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    @Column(nullable = false)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Permission> permissions;

    @ManyToMany(mappedBy = "roles")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<User> users;

}
