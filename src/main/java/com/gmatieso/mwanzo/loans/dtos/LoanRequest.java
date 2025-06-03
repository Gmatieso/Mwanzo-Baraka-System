package com.gmatieso.mwanzo.loans.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record LoanRequest(
        BigDecimal amount,
        LocalDateTime loanDate,
        LocalDateTime repaymentPeriod,
        Long memberId,
        List<Long> guarantorId
) {
}
