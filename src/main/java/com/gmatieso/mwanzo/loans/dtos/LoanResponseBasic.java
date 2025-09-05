package com.gmatieso.mwanzo.loans.dtos;

import com.gmatieso.mwanzo.common.utils.StatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LoanResponseBasic(
        Long id,
        BigDecimal amount,
        LocalDateTime loanDate,
        LocalDateTime repaymentPeriod,
        StatusEnum statusEnum
) {
}
