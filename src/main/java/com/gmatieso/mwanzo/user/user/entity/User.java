package com.gmatieso.mwanzo.user.user.entity;

import com.gmatieso.mwanzo.user.role.enums.RoleEnum;
import com.gmatieso.mwanzo.membership.entity.Member;
import com.gmatieso.mwanzo.user.role.models.Role;
import com.gmatieso.mwanzo.user.user.enums.UserRole;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(
        name = "users",
        indexes = {
                @Index(name = "idx_user_email", columnList = "email"),
                @Index(name = "idx_user_phone", columnList = "phone"),
                @Index(name = "idx_user_username", columnList = "username")
        }
 )
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;


    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    private String phone;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Member> members = new ArrayList<>();

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole role;


    @Column(name = "username", nullable = false, unique = true)
    private String username;


    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (getRole() != null) {
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + getRole().name()));
        } else {
            var authorities = roles.stream()
                    .flatMap(role -> role.getPermissions().stream()
                            .map(permission -> new SimpleGrantedAuthority(permission.getName())))
                    .collect(Collectors.toList());
            authorities.addAll(roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).toList());
            return authorities;
        }
    }
}
