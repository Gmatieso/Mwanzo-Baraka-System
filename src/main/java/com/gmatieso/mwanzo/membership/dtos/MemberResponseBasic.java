package com.gmatieso.mwanzo.membership.dtos;

import com.gmatieso.mwanzo.common.utils.MemberType;
import com.gmatieso.mwanzo.common.utils.Status;
import com.gmatieso.mwanzo.security.dtos.UserResponse;

import java.math.BigDecimal;

public record MemberResponseBasic(
        Long id,
        MemberType memberType,
        UserResponse user,
        BigDecimal registrationFees,
        Status status
) {
}
