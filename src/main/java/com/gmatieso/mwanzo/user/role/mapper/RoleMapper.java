package com.gmatieso.mwanzo.user.role.mapper;

import com.gmatieso.mwanzo.user.role.dtos.RoleRequest;
import com.gmatieso.mwanzo.user.role.dtos.RoleResponse;
import com.gmatieso.mwanzo.user.role.enums.RoleEnum;
import com.gmatieso.mwanzo.user.role.models.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoleMapper {
        Role toEntity(RoleRequest roleRequest);

        RoleResponse toResponse(Role role);

    @Named("mapRolesToRoleEnums")
    default List<RoleEnum> mapRolesToRoleEnums(List<Role> roles) {
        if (roles == null) {
            return null;
        }
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }
}
