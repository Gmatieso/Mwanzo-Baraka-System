package com.gmatieso.mwanzo.loans.dtos;

import java.math.BigDecimal;

public record RepaymentResponse(
        Long id,
        BigDecimal repaymentAmount,
        BigDecimal interestPaid,
        BigDecimal principalPaid,
        LoanResponseBasic loan
) {
}
