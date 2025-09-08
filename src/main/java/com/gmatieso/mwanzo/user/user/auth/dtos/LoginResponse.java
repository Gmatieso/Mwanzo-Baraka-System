package com.gmatieso.mwanzo.user.user.auth.dtos;

import com.gmatieso.mwanzo.user.role.enums.RoleEnum;

import java.util.List;
import java.util.UUID;

public record LoginResponse(
        UUID id,
        String email,
        String FirstName,
        String LastName,
        String accessToken,
        String refreshToken,
        Long expiresAt,
        List<RoleEnum> roles,
        List<String> permissions) {
}
