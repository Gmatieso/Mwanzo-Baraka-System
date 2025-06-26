package com.gmatieso.mwanzo.membership.dtos;

import jakarta.validation.constraints.NotEmpty;

public record GroupMemberRequest(
       @NotEmpty(message = "Group Member name is required") String name
) {
}
