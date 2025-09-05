package com.gmatieso.mwanzo.user.role.services;

import com.gmatieso.mwanzo.user.role.dtos.RoleRequest;
import com.gmatieso.mwanzo.user.role.enums.RoleEnum;
import com.gmatieso.mwanzo.user.role.models.Role;
import com.gmatieso.mwanzo.user.user.entity.User;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface RoleService {
        ResponseEntity<?> addRole(RoleRequest roleRequest);

        ResponseEntity<?> getAllRoles(Pageable pageable, RoleEnum  name);

        ResponseEntity<?> editRole(UUID roleId, @Valid  RoleRequest roleRequest);

        ResponseEntity<?> deleteRole(UUID roleId);

        List<Role> getRolesById(List<UUID> rolesIds);

        List<Role> getRolesByNames(List<RoleEnum> rolesNames);

        Role getRoleByNameOrThrow(RoleEnum roleEnum);

        void validateExistingRoles(List<UUID> inputRoleIds, List<Role> existingRoles);

        List<Role> createRolesInit(List<RoleRequest> roleRequests);

        Role getRoleByIdOrThrow(UUID roleId);

        void updateUserRoles(User user, List<UUID> newRoleIds);

        void addUserRoles(User newUser, List<UUID> roleIds);

        ResponseEntity<?> getRoleDetailsById(UUID id);

        ResponseEntity<?> getRolesGrouping();

        Boolean isAdminOrSuperAdmin(User user);

}
