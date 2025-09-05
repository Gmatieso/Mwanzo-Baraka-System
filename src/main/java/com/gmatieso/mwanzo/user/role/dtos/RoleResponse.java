package com.gmatieso.mwanzo.user.role.dtos;

import com.gmatieso.mwanzo.user.permission.dtos.PermissionResponse;

import java.util.List;
import java.util.UUID;

public record RoleResponse(UUID id, String name, String description, List<PermissionResponse> permissions) {
}
