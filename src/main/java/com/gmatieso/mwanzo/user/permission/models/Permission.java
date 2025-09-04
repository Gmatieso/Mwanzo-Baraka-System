package com.gmatieso.mwanzo.user.permission.models;


import com.gmatieso.mwanzo.user.permission_group.models.PermissionGroup;
import com.gmatieso.mwanzo.user.role.models.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "permissions")
@Data
public class Permission {
    @Id
    @UuidGenerator
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_group_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private PermissionGroup permissionGroup;

    @ManyToMany(mappedBy = "permissions")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Role> roles;





}
