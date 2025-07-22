package com.gmatieso.mwanzo.membership.dtos;

import com.gmatieso.mwanzo.security.dtos.UserResponse;

import java.util.List;

public record MemberResponse(
        Long id,
        UserResponse user,
        List<ContributionResponse> contribution,
        ShareResponse share
) {
}
