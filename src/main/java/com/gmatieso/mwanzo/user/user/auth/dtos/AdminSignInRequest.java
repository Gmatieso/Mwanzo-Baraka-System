package com.gmatieso.mwanzo.user.user.auth.dtos;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public record AdminSignInRequest(
        @NotEmpty
        @Email
        String email,

        @NotEmpty
        @Length(min = 8)
        String password
) {
}
