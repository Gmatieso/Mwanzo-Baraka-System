package com.gmatieso.mwanzo.membership.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotEmpty;



public record ContributionRequest(
        @NotEmpty String memberId,
        @NotEmpty BigDecimal amount,
        LocalDateTime contributionDate,
        BigDecimal groupShareAmount,
        BigDecimal individualShareAmount
) {
}
