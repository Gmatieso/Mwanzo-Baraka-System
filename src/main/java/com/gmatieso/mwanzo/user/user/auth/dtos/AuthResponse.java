package com.gmatieso.mwanzo.user.user.auth.dtos;

public record AuthResponse(String accessToken, String refreshToken, Long expiresAt) {
}
