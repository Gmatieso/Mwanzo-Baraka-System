package com.gmatieso.mwanzo.membership.dtos;

import com.gmatieso.mwanzo.user.dtos.UserResponse;

import java.util.List;

public record MemberResponse(
        Long id,
        UserResponse user,
        List<ContributionResponse> contribution,
        ShareResponse share
) {
}
