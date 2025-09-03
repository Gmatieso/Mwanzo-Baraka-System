package com.gmatieso.mwanzo.user.dtos;

public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
//        List<MemberResponseBasic> members,
        String username) {
}
