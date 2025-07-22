package com.gmatieso.mwanzo.membership.dtos;

import com.gmatieso.mwanzo.common.utils.MemberType;
import com.gmatieso.mwanzo.security.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record MemberRequest(

        Long user_id,
//        List<Long> contributionId,
        BigDecimal registrationFees,
        LocalDateTime registrationDate,
        MemberType memberType
) {
}
