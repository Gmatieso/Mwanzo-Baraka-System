package com.gmatieso.mwanzo.user.user.auth.dtos;

public record AuthTokenDto(
        String firstName,
        String secondName,
        String phoneNumber,
        String email,
        String token
) {
}
