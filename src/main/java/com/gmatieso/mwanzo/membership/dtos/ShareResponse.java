package com.gmatieso.mwanzo.membership.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ShareResponse(
        Long id,
        BigDecimal totalShares,
        List<MemberResponse> members,
        LocalDateTime lastUpdated

) {
}
