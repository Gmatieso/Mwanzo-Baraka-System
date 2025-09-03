package com.gmatieso.mwanzo.user.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = "First name is required")
        String firstName,
        @NotBlank(message = "Last name is required")
        String lastName,
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        String email,
        @NotBlank(message = "Phone number is required")
        @Size(min = 10, max = 15, message = "Phone number Must be between 10 and 15 digits")
        @Pattern(
                regexp = "^[0-9+\\-() ]+$",
                message = "Phone number can only contain digits, +, -, spaces, or parentheses"
        )
        String phone,

        @NotBlank(message = "Username is required")
        String username,
        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password) {
}
