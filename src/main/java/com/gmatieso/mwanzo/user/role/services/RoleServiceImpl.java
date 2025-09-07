package com.gmatieso.mwanzo.user.role.services;

import com.gmatieso.mwanzo.common.exception.BadRequestException;
import com.gmatieso.mwanzo.common.exception.ResourceNotFoundException;
import com.gmatieso.mwanzo.common.response.ApiResponseEntity;
import com.gmatieso.mwanzo.user.permission.models.Permission;
import com.gmatieso.mwanzo.user.permission.repositories.PermissionRepository;
import com.gmatieso.mwanzo.user.role.dtos.RoleRequest;
import com.gmatieso.mwanzo.user.role.dtos.RoleResponse;
import com.gmatieso.mwanzo.user.role.enums.RoleEnum;
import com.gmatieso.mwanzo.user.role.enums.RoleGroup;
import com.gmatieso.mwanzo.user.role.mapper.RoleMapper;
import com.gmatieso.mwanzo.user.role.models.Role;
import com.gmatieso.mwanzo.user.role.repositories.RoleRepository;
import com.gmatieso.mwanzo.user.user.entity.User;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.gmatieso.mwanzo.common.utils.CommonUtils.createPage;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RoleMapper roleMapper;

    @Override
    public ResponseEntity<?> addRole(RoleRequest roleRequest) {

        ensureRoleDoesNotExistByName(roleRequest);

        List<Permission> permissions = getPermissions(roleRequest.permissionIds());

        checkIfPermissionsExist(permissions, roleRequest);

        Role newRole = roleMapper.toEntity(roleRequest);

        newRole.setPermissions(permissions);

        roleRepository.save(newRole);

        return ApiResponseEntity.success("Role added successfully", roleMapper.toResponse(newRole));
    }

    @Override
    public List<Role> createRolesInit(List<RoleRequest> roleRequests) {
        Set<RoleEnum> requestedRoleNames = roleRequests.stream()
                .map(RoleRequest::name)
                .collect(Collectors.toSet());

        List<Role> existingRoles = roleRepository.findRoleByNameIn(requestedRoleNames);
        Map<RoleEnum, Role> existingRoleMap = existingRoles.stream()
                .collect(Collectors.toMap(Role::getName, role -> role));

        List<Role> rolesToUpdate = new ArrayList<>();
        List<RoleRequest> newRoles = new ArrayList<>();

        for (RoleRequest roleRequest : roleRequests) {
            Role existingRole = existingRoleMap.get(roleRequest.name());

            if (existingRole == null) {
                newRoles.add(roleRequest);
            } else {
                Set<UUID> existingPermissionIds = existingRole.getPermissions().stream()
                        .map(Permission::getId)
                        .collect(Collectors.toSet());

                Set<UUID> missingPermissions = new HashSet<>(roleRequest.permissionIds());
                missingPermissions.removeAll(existingPermissionIds);

                if (!missingPermissions.isEmpty()) {
                    List<Permission> newPermissions = permissionRepository.findAllById(missingPermissions);
                    existingRole.getPermissions().addAll(newPermissions);
                    rolesToUpdate.add(existingRole);
                }
            }
        }

        List<Role> savedRoles = newRoles.isEmpty() ? Collections.emptyList() : saveRoles(newRoles);

        if (!rolesToUpdate.isEmpty()) {
            roleRepository.saveAll(rolesToUpdate);
        }

        List<Role> allUpdatedRoles = new ArrayList<>(savedRoles);
        allUpdatedRoles.addAll(rolesToUpdate);
        return allUpdatedRoles;
    }

    private List<Role> saveRoles(List<RoleRequest> roleRequests) {
        List<Role> roles = roleRequests.stream().map(roleRequest -> {
            Role role = roleMapper.toEntity(roleRequest);
            List<Permission> permissions = permissionRepository.findAllById(roleRequest.permissionIds());
            role.setPermissions(permissions);
            return role;
        }).toList();

        roleRepository.saveAll(roles);
        return roles;
    }


    private @NotNull List<Permission> getPermissions(List<UUID> permissionIds) {
        return permissionRepository.findAllById(permissionIds);
    }

    @Override
    public ResponseEntity<?> getAllRoles(Pageable pageable, RoleEnum name) {
        Page<Role> roles = roleRepository.findAllByName(pageable, name);
        List<RoleResponse> rolesResponse = roles.getContent().stream().map(roleMapper::toResponse).toList();
        return ApiResponseEntity.success("Roles", createPage(rolesResponse, pageable, roles.getTotalElements()));
    }

    @Override
    public ResponseEntity<?> editRole(UUID roleId, RoleRequest roleRequest) {
        Role role = getRoleByIdOrThrow(roleId);
        if(!roleRequest.name().equals(role.getName())) {
            role.setName(roleRequest.name());
        }
        if(!roleRequest.description().equals(role.getDescription())) {
            role.setDescription(roleRequest.description());
        }

        List<Permission> existingPermissions = role.getPermissions();
        List<Permission> updatePermissions = getPermissions(roleRequest.permissionIds());

        List<Permission> permissionsToAdd = updatePermissions.stream()
                .filter(permission -> !existingPermissions.contains(permission))
                .toList();

        List<Permission> permissionsToRemove = existingPermissions.stream()
                .filter(permission -> !updatePermissions.contains(permission))
                .toList();

        existingPermissions.removeAll(permissionsToRemove);
        existingPermissions.addAll(permissionsToAdd);

        roleRepository.save(role);

        return ApiResponseEntity.success("Role updated successfully", roleMapper.toResponse(role));
    }

    @Transactional
    @Override
    public ResponseEntity<?> deleteRole(UUID roleId) {
        Role role = getRoleByIdOrThrow(roleId);
        if (!role.getUsers().isEmpty()) {
            throw new BadRequestException("Cannot delete role. It is assigned to users.");
        }

        roleRepository.delete(role);
        return ApiResponseEntity.success("Role deleted successfully", roleMapper.toResponse(role));
    }

    @Override
    public List<Role> getRolesById(List<UUID> roleIds) {
        return roleRepository.findAllById(roleIds);
    }

    @Override
    public List<Role> getRolesByNames(List<RoleEnum> roleNames) {
        return roleRepository.findRoleByNameIn(new HashSet<>(roleNames));
    }

    @Override
    public Role getRoleByNameOrThrow(RoleEnum roleEnum) {
        return roleRepository.findByName(roleEnum).orElseThrow(
                () -> new ResourceNotFoundException("Role does not exist.")
        );
    }

    @Override
    public void validateExistingRoles(List<UUID> inputRoleIds, List<Role> existingRoles) {
        Set<UUID> roleIds = existingRoles.stream().map(Role::getId).collect(Collectors.toSet());
        List<UUID> missingRoleIds = inputRoleIds.stream().filter(roleId -> !roleIds.contains(roleId)).toList();
        if (!missingRoleIds.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Roles with IDs [" + String.join(", ", missingRoleIds.stream().map(UUID::toString).toList()) + "] do not exist"
            );
        }
    }

    @Override
    public Role getRoleByIdOrThrow(UUID roleId) {
        return roleRepository.findById(roleId).orElseThrow(
                () -> new ResourceNotFoundException("Role with id " + roleId + " not found")
        );
    }

    @Override
    public void updateUserRoles(User user, List<UUID> newRoleIds) {

    }


    private void validateRoleIds(List<UUID> roleIds) {
        if (roleIds == null || roleIds.isEmpty()) {
            throw new IllegalArgumentException("User must have at least one role.");
        }
    }

    private boolean hasRole(List<Role> roles, Set<RoleEnum> targetRoles) {
        if (roles == null || roles.isEmpty()) {
            return false;
        }
        return roles.stream().map(Role::getName).anyMatch(targetRoles::contains);
    }


    @Override
    public Boolean isAdminOrSuperAdmin(User user) {
        return isAdmin(user) || isSuperAdmin(user);
    }



    private Boolean isAdmin(User user) {
        return hasRole(user, RoleEnum.ADMIN);
    }

    private Boolean isSuperAdmin(User user) {
        return hasRole(user, RoleEnum.SUPER_ADMIN);
    }

    @Override
    public ResponseEntity<?> getRoleDetailsById(UUID id) {
        Role role = getRoleByIdOrThrow(id);
        return ApiResponseEntity.success("Role", roleMapper.toResponse(role));
    }

    @Override
    public ResponseEntity<?> getRolesGrouping() {
        Map<RoleGroup, Set<RoleEnum>> roleGroups = RoleGroup.getAllRoles();
        return ApiResponseEntity.success("Role Groups", roleGroups);
    }

    @Override
    public void addUserRoles(User newUser, List<UUID> roleIds) {
        validateRoleIds(roleIds);
        List<Role> roles = getRolesById(roleIds);
        newUser.setRoles(new ArrayList<>(roles));
    }

    private static Boolean hasRole(@NotNull User user, @NotNull RoleEnum roleEnum) {
        return user.getRoles().stream().map(Role::getName).anyMatch(roleEnum::equals);
    }

    private List<Role> filterRolesByName(List<Role> roles, RoleEnum roleEnum) {
        return roles.stream()
                .filter(role -> role.getName().equals(roleEnum))
                .toList();
    }

    private void ensureRoleDoesNotExistByName(RoleRequest roleRequest) {
        if (roleRepository.existsByName(roleRequest.name())) {
            throw new BadRequestException("Role with name " + roleRequest.name() + " already exists");
        }
    }

    private void checkIfPermissionsExist(List<Permission> permissions, RoleRequest roleRequest) {
        List<UUID> existingPermissionsIds = permissions.stream().map(Permission::getId).toList();
        Set<UUID> missingPermissionsIds = roleRequest.permissionIds().stream()
                .filter(permissionId -> !existingPermissionsIds.contains(permissionId)).collect(Collectors.toSet());
        if (!missingPermissionsIds.isEmpty()) {
            throw new BadRequestException("Permissions with id " + missingPermissionsIds + " not found");
        }
    }
}

