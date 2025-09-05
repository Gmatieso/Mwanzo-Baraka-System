package com.gmatieso.mwanzo.user.permission_group.dtos;

import com.gmatieso.mwanzo.user.permission.dtos.PermissionResponse;

import java.util.List;
import java.util.UUID;

public record PermissionGroupResponse(UUID id, String name, List<PermissionResponse> permissions) {
}
