package com.gmatieso.mwanzo.membership.dtos;

import com.gmatieso.mwanzo.common.utils.MemberType;

import java.math.BigDecimal;

public record MemberResponseBasic(
        Long id,
        String name,
        MemberType memberType,
        BigDecimal registrationFees
) {
}
