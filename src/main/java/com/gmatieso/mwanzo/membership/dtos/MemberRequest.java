package com.gmatieso.mwanzo.membership.dtos;

import com.gmatieso.mwanzo.common.utils.MemberType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record MemberRequest(

        String name,
//        List<Long> contributionId,
        BigDecimal registrationFees,
        LocalDateTime registrationDate,
        MemberType memberType
) {
}
