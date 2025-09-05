package com.gmatieso.mwanzo.user.permission_group.services;

import com.gmatieso.mwanzo.user.permission_group.dtos.PermissionGroupRequest;
import com.gmatieso.mwanzo.user.permission_group.models.PermissionGroup;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PermissionGroupService {

    List<PermissionGroup> createPermissionGroups(List<PermissionGroupRequest> permissionGroupRequests);

    ResponseEntity<?> getAllPermissionGroups();
}
