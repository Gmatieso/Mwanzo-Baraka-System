package com.gmatieso.mwanzo.membership.dtos;

import com.gmatieso.mwanzo.common.utils.MemberTypeEnum;
import com.gmatieso.mwanzo.common.utils.StatusEnum;
import com.gmatieso.mwanzo.user.user.dtos.UserResponse;

import java.math.BigDecimal;

public record MemberResponseBasic(
        Long id,
        MemberTypeEnum memberTypeEnum,
        UserResponse user,
        BigDecimal registrationFees,
        StatusEnum statusEnum
) {
}
