package com.gmatieso.mwanzo.membership.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ContributionResponse(
    Long id,
    String memberId,
    BigDecimal amount,
    BigDecimal groupShareAmount,
    BigDecimal individualShareAmount,
    LocalDateTime contributionDate
){

}
