package com.gmatieso.mwanzo.security.dtos;

import com.gmatieso.mwanzo.membership.dtos.MemberResponse;

import java.util.List;

public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        List<MemberResponse> members
        ) {
}
