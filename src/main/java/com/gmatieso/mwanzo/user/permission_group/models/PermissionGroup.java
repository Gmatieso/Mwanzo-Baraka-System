package com.gmatieso.mwanzo.user.permission_group.models;

import com.gmatieso.mwanzo.user.permission.models.Permission;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "permission_groups")
@Data
public class PermissionGroup {
    @Id
    @UuidGenerator
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "permissionGroup", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Permission> permissions = new ArrayList<>();

}
