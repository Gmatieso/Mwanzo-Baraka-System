package com.gmatieso.mwanzo.security.entity;

import com.gmatieso.mwanzo.common.utils.Role;
import com.gmatieso.mwanzo.membership.entity.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
public class User {
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


    @Column(name = "username", nullable = false, unique = true)
    private String username;


    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role")
    private Role role;
}
