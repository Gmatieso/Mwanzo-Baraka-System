package com.gmatieso.mwanzo.membership.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record GroupResponseBasic(
        Long id,
        String registrationNumber,
        String name,
        String email,
        String location,
        LocalDateTime dateRegistered,
        String postalAddress,
        String groupCategory,
        String sector,
        String phone,
        BigDecimal registrationFee,
        List<MemberResponseBasic> members
) {
}
