package com.gmatieso.mwanzo.membership.dtos;

import com.gmatieso.mwanzo.common.utils.MemberType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record MemberRequest(

        @NotEmpty(message = "Name is required") String name,
//        List<Long> contributionId,
        @NotNull(message = "Registration fees is required") BigDecimal registrationFees,
        @NotNull(message = "Registration date is required") LocalDateTime registrationDate,
        @NotNull(message = "Member type is required") MemberType memberType,
        List<GroupMemberRequest> members  // Optional, required only for GROUP
) {
}
