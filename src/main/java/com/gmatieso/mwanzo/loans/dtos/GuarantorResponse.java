package com.gmatieso.mwanzo.loans.dtos;

import com.gmatieso.mwanzo.membership.dtos.MemberResponseBasic;

public record GuarantorResponse(
        Long id,
        MemberResponseBasic memberResponse
) {
}
