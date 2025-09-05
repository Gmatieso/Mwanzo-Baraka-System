package com.gmatieso.mwanzo.user.user.entity;

import com.gmatieso.mwanzo.common.utils.TokenTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "tokens")
@Data
public class Token {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(unique = true, nullable = false)
    private String token;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TokenTypeEnum tokenTypeEnum;

    @NotNull
    private boolean expired = false;

    @NotNull
    private boolean invalid = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    public User user;
}
