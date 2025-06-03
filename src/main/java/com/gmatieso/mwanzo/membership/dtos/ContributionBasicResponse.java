package com.gmatieso.mwanzo.membership.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ContributionBasicResponse(
        Long id,
        BigDecimal amount,
        BigDecimal groupShareAmount,
        BigDecimal individualShareAmount,
        LocalDateTime contributionDate
) {
}
