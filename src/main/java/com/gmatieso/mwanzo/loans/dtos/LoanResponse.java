package com.gmatieso.mwanzo.loans.dtos;

import com.gmatieso.mwanzo.common.utils.Status;
import com.gmatieso.mwanzo.membership.dtos.MemberResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record LoanResponse(
        Long id,
        BigDecimal amount,
        LocalDateTime loanDate,
        LocalDateTime repaymentPeriod,
        Status status,
        List<MemberResponse> members,
        List<GuarantorResponse> guarantor,
        List<PenaltyResponse> penalty,
        List<RepaymentResponse> repayments

) {
}
