package com.gmatieso.mwanzo.user.permission_group.services;

import com.gmatieso.mwanzo.common.response.ApiResponseEntity;
import com.gmatieso.mwanzo.user.permission.models.Permission;
import com.gmatieso.mwanzo.user.permission_group.dtos.PermissionGroupRequest;
import com.gmatieso.mwanzo.user.permission_group.dtos.PermissionGroupResponse;
import com.gmatieso.mwanzo.user.permission_group.mappers.PermissionGroupMapper;
import com.gmatieso.mwanzo.user.permission_group.models.PermissionGroup;
import com.gmatieso.mwanzo.user.permission_group.repositories.PermissionGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PermissionGroupServiceImpl implements PermissionGroupService {

    private final PermissionGroupRepository permissionGroupRepository;

    private final PermissionGroupMapper permissionGroupMapper;

    @Override
    public List<PermissionGroup> createPermissionGroups(List<PermissionGroupRequest> permissionGroupRequests) {
        List<String> permissionGroupNames = permissionGroupRequests.stream().map(PermissionGroupRequest::name).toList();
        Map<String, PermissionGroup> permissionGroupMap = permissionGroupRepository.findByNameIn(permissionGroupNames).stream().collect(Collectors.toMap(PermissionGroup::getName, Function.identity()));

        List<PermissionGroup> newPermissionGroups = permissionGroupRequests.stream().map(request -> {
            PermissionGroup permissionGroup = permissionGroupMap.computeIfAbsent(request.name(), key -> {
                PermissionGroup newGroup = new PermissionGroup();
                newGroup.setName(key);
                return newGroup;
            });

            Set<String> existingPermissionNames = permissionGroup.getPermissions().stream()
                    .map(Permission::getName)
                    .collect(Collectors.toSet());

            List<Permission> newPermissions = request.permissions().stream()
                    .filter(permission -> !existingPermissionNames.contains(permission.name()))
                    .map(permission -> {
                        Permission newPermission = new Permission();
                        newPermission.setName(permission.name());
                        newPermission.setPermissionGroup(permissionGroup);
                        return newPermission;
                    })
                    .toList();

            permissionGroup.getPermissions().addAll(newPermissions);
            return permissionGroup;
        }).toList();

        permissionGroupRepository.saveAll(newPermissionGroups);

        return newPermissionGroups;
    }

    @Override
    public ResponseEntity<?> getAllPermissionGroups() {
        List<PermissionGroup> permissionGroups = permissionGroupRepository.findAll();
        List<PermissionGroupResponse> response = permissionGroups.stream().map(
                permissionGroupMapper::toDto
        ).toList();
        return ApiResponseEntity.success("Retrieved permission groups successfully", response);
    }
}
