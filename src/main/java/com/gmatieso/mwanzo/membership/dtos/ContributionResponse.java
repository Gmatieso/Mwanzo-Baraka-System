package com.gmatieso.mwanzo.membership.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ContributionResponse(
    Long id,
    List<MemberResponse> members,
    BigDecimal amount,
    BigDecimal groupShareAmount,
    BigDecimal individualShareAmount,
    LocalDateTime contributionDate
){

}
