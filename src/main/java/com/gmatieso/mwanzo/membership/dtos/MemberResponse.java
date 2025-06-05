package com.gmatieso.mwanzo.membership.dtos;

import java.util.List;

public record MemberResponse(
        Long id,
        String name,
        List<ContributionResponse> contribution,
        ShareResponse share
) {
}
