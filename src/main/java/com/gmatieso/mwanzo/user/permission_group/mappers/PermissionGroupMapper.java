package com.gmatieso.mwanzo.user.permission_group.mappers;

import com.gmatieso.mwanzo.user.permission_group.dtos.PermissionGroupResponse;
import com.gmatieso.mwanzo.user.permission_group.models.PermissionGroup;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionGroupMapper {
   PermissionGroupResponse  toDto(PermissionGroup permissionGroup);
}
