package com.gmatieso.mwanzo.membership.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record GroupResponseBasic(
        Long id,
        String registrationNumber,
        String email,
        String location,
        LocalDateTime dateRegistered,
        String postalAddress,
        String groupCategory,
        String sector,
        List<MemberResponseBasic> member
) {
}
