package com.gmatieso.mwanzo.user.user.auth.dtos;

public record LoginRequest(String email, String password, String otp) {
}
