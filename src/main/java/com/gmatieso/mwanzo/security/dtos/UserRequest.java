package com.gmatieso.mwanzo.security.dtos;

public record UserRequest(
        String firstName,
        String lastName,
        String email,
        String phone) {
}
