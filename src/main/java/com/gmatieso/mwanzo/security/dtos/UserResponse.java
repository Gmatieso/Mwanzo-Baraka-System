package com.gmatieso.mwanzo.security.dtos;

public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone) {
}
