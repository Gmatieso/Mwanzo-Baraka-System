package com.gmatieso.mwanzo.loans.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PenaltyResponse(
        Long id,
        LocalDateTime penaltyDate,
        BigDecimal amount,
        LoanResponseBasic loanResponse
) {
}
