package com.gmatieso.mwanzo.user.permission_group.dtos;

import com.gmatieso.mwanzo.user.permission.dtos.PermissionRequest;

import java.util.List;

public record PermissionGroupRequest(String name, List<PermissionRequest> permissions) {
}
