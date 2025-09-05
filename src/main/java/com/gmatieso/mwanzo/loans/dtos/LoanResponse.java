package com.gmatieso.mwanzo.loans.dtos;

import com.gmatieso.mwanzo.common.utils.StatusEnum;
import com.gmatieso.mwanzo.membership.dtos.MemberResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record LoanResponse(
        Long id,
        BigDecimal amount,
        LocalDateTime loanDate,
        LocalDateTime repaymentPeriod,
        StatusEnum statusEnum,
        List<MemberResponse> members,
        List<GuarantorResponse> guarantor,
        List<PenaltyResponse> penalty,
        List<RepaymentResponse> repayments

) {
}
