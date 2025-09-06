package com.gmatieso.mwanzo.user.permission_group.configs;

import com.gmatieso.mwanzo.common.exception.ResourceNotFoundException;
import com.gmatieso.mwanzo.user.permission.dtos.PermissionRequest;
import com.gmatieso.mwanzo.user.permission.models.Permission;
import com.gmatieso.mwanzo.user.permission.repositories.PermissionRepository;
import com.gmatieso.mwanzo.user.permission_group.dtos.PermissionGroupRequest;
import com.gmatieso.mwanzo.user.permission_group.services.PermissionGroupService;
import com.gmatieso.mwanzo.user.role.dtos.RoleRequest;
import com.gmatieso.mwanzo.user.role.enums.RoleEnum;
import com.gmatieso.mwanzo.user.role.models.Role;
import com.gmatieso.mwanzo.user.role.repositories.RoleRepository;
import com.gmatieso.mwanzo.user.role.services.RoleService;
import com.gmatieso.mwanzo.user.user.dtos.UserRequest;
import com.gmatieso.mwanzo.user.user.service.userservice.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.gmatieso.mwanzo.user.permission.enums.PermissionEnum.*;


@Component
@AllArgsConstructor
public class PermissionInitializer {
    private static  final Logger logger = LoggerFactory.getLogger(PermissionInitializer.class);


    private final boolean initEnabled;
    private final PermissionGroupService permissionGroupService;
    private final RoleService roleService;
    private final PermissionRepository permissionRepository;
    private final UserService userService;
    private final RoleRepository roleRepository;

    @Bean
    public ApplicationRunner init() {
        return args -> {
            if(!initEnabled){
                return;
            }
            try{
                List<PermissionGroupRequest> permissionGroupRequestList = getDefaultPermissionGroups();
                permissionGroupService.createPermissionGroups(permissionGroupRequestList);
            }catch (Exception e){
                logger.error("Unexpected error during permissions initialization: " + e.getMessage(), e);
            }

            try {
                List<Role> userRoles = initRoles(roleService, permissionRepository);
            } catch (Exception e) {
                logger.error("Unexpected error during roles initialization: " + e.getMessage(), e);
            }

            try {
                initSuperAdmin(userService);
            } catch (Exception e) {
                logger.error("Unexpected error during user initialization: " + e.getMessage(), e);
            }


        };

    }


    private PermissionGroupRequest buildDefaultPermissionGroupRequest(String permissionGroupName, List<String> permissionRequestName) {
        List<PermissionRequest> permissionRequests =  permissionRequestName.stream().map(PermissionRequest::new).toList();
        return new PermissionGroupRequest(permissionGroupName, permissionRequests);
    }

    private List<Role> initRoles(RoleService roleService, PermissionRepository permissionRepository) {
       List<UUID> adminPermissionsIds = permissionRepository.findByNameIn(getSuperAdminPermissions()).stream().map(Permission::getId).toList();
       List<UUID> treasurerPermissionsIds = permissionRepository.findByNameIn(getTreasurerPermissions()).stream().map(Permission::getId).toList();
       List<UUID> auditorPermissionsIds = permissionRepository.findByNameIn(getAuditorPermissions()).stream().map(Permission::getId).toList();
       List<UUID> loanOfficerPermissionsIds = permissionRepository.findByNameIn(getLoanOfficerPermissions()).stream().map(Permission::getId).toList();
        List<UUID> creditCommittePermissionsIds = permissionRepository.findByNameIn(getCreditCommittePermissions()).stream().map(Permission::getId).toList();
        List<UUID> individualMemberPermissionsIds = permissionRepository.findByNameIn(getIndividualMemberPermissions()).stream().map(Permission::getId).toList();
        List<UUID> groupRepresentativePermissionsIds = permissionRepository.findByNameIn(getGroupRepresentativePermissions()).stream().map(Permission::getId).toList();
        List<UUID> groupMemberPermissionsIds = permissionRepository.findByNameIn(getGroupMemberPermissions()).stream().map(Permission::getId).toList();

        List<RoleRequest> userRoles = List.of(
                new RoleRequest(RoleEnum.SUPER_ADMIN, "Has full system access and administrative rights", adminPermissionsIds),
                new RoleRequest(RoleEnum.ADMIN, "Manages users, roles, and high-level system operations.", adminPermissionsIds),
                new RoleRequest(RoleEnum.TREASURER,"Handles financial records, including reg fees, monthly contribution, loans repayments, penalties and dividends calculation",treasurerPermissionsIds),
                new RoleRequest(RoleEnum.AUDITOR,"Oversees financial activities, Review reports, transactions, and loan performance",auditorPermissionsIds),
                new RoleRequest(RoleEnum.LOAN_OFFICER,"Review and approves loan applications, calculates member loan eligibility, sets repayment schedules & monitor repayments",loanOfficerPermissionsIds),
                new RoleRequest(RoleEnum.CREDIT_COMMITTEE,"Provides oversight for loan approvals",creditCommittePermissionsIds),
                new RoleRequest(RoleEnum.INDIVIDUAL_MEMBER,"Registers as an individual",individualMemberPermissionsIds),
                new RoleRequest(RoleEnum.GROUP_REPRESENTATIVE,"Registers as Group, manages group and member details, Oversees contributions, applies group loans on behalf of members",groupRepresentativePermissionsIds),
                new RoleRequest(RoleEnum.GROUP_MEMBER,"Belongs to a registered group",groupMemberPermissionsIds)
        );
        return roleService.createRolesInit(userRoles);
    }

    private List<String> getSuperAdminPermissions() {
        return Arrays.asList(
                ADMIN_READ.getPermission(),
                ADMIN_WRITE.getPermission(),
                ADMIN_CREATE.getPermission(),
                TREASURER_READ.getPermission(),
                TREASURER_WRITE.getPermission(),
                TREASURER_CREATE.getPermission(),
                AUDITOR_READ.getPermission(),
                AUDITOR_WRITE.getPermission(),
                AUDITOR_CREATE.getPermission(),
                LOAN_OFFICER_READ.getPermission(),
                LOAN_OFFICER_WRITE.getPermission(),
                LOAN_OFFICER_CREATE.getPermission(),
                CREDIT_COMMITTEE_READ.getPermission(),
                CREDIT_COMMITTEE_WRITE.getPermission(),
                CREDIT_COMMITTEE_CREATE.getPermission(),
                INDIVIDUAL_MEMBER_READ.getPermission(),
                INDIVIDUAL_MEMBER_WRITE.getPermission(),
                INDIVIDUAL_MEMBER_CREATE.getPermission(),
                GROUP_REPRESENTATIVE_READ.getPermission(),
                GROUP_REPRESENTATIVE_WRITE.getPermission(),
                GROUP_REPRESENTATIVE_CREATE.getPermission(),
                GROUP_MEMBER_READ.getPermission(),
                GROUP_MEMBER_WRITE.getPermission(),
                GROUP_MEMBER_CREATE.getPermission(),
                ROLE_READ.getPermission(),
                ROLE_WRITE.getPermission(),
                ROLE_CREATE.getPermission()
        );
    }


    private List<String> getTreasurerPermissions(){
        return Arrays.asList(
                TREASURER_READ.getPermission(),
                TREASURER_WRITE.getPermission(),
                TREASURER_CREATE.getPermission()
        );
    }

    private List<String> getAuditorPermissions(){
        return  Arrays.asList(
                AUDITOR_READ.getPermission(),
                AUDITOR_WRITE.getPermission(),
                AUDITOR_CREATE.getPermission()
        );
    }

    private List<String> getLoanOfficerPermissions(){
        return Arrays.asList(
                LOAN_OFFICER_READ.getPermission(),
                LOAN_OFFICER_WRITE.getPermission(),
                LOAN_OFFICER_CREATE.getPermission()
        );
    }

    private List<String> getCreditCommittePermissions(){
        return Arrays.asList(
                CREDIT_COMMITTEE_READ.getPermission(),
                CREDIT_COMMITTEE_WRITE.getPermission(),
                CREDIT_COMMITTEE_CREATE.getPermission()
        );
    }

    private List<String> getIndividualMemberPermissions(){
        return Arrays.asList(
                INDIVIDUAL_MEMBER_READ.getPermission(),
                INDIVIDUAL_MEMBER_WRITE.getPermission(),
                INDIVIDUAL_MEMBER_CREATE.getPermission()
        );
    }

    private List<String> getGroupRepresentativePermissions(){
        return Arrays.asList(
                GROUP_REPRESENTATIVE_READ.getPermission(),
                GROUP_REPRESENTATIVE_WRITE.getPermission(),
                GROUP_REPRESENTATIVE_CREATE.getPermission()
        );
    }

    private List<String> getGroupMemberPermissions(){
        return Arrays.asList(
                GROUP_MEMBER_READ.getPermission(),
                GROUP_MEMBER_WRITE.getPermission(),
                GROUP_MEMBER_WRITE.getPermission()
        );
    }



    private void initSuperAdmin(UserService userService) {
        try {
           Role  superAdminRole = roleRepository.findByName(RoleEnum.SUPER_ADMIN).orElseThrow(
                   () -> new ResourceNotFoundException("Super administrator role does not exist.")
           );

            UserRequest userRequest = new UserRequest(
                    "mwanzo",
                    "admin",
                    "developer.mwanzobaraka.co.ke",
                    "0707575938",
                    null,
                    null
            );
            userService.createUser(userRequest);
        } catch (Exception e){
            logger.warn(e.getMessage());
        }
    }

    private List<PermissionGroupRequest> getDefaultPermissionGroups() {
        return List.of(
                buildDefaultPermissionGroupRequest("Admin Management", generatePermissionNames("ADMIN")),
                buildDefaultPermissionGroupRequest("Treasurer  Management", generatePermissionNames("TREASURER")),
                buildDefaultPermissionGroupRequest("Auditor  Management", generatePermissionNames("AUDITOR")),
                buildDefaultPermissionGroupRequest("Loan_Officer Management", generatePermissionNames("LOAN_OFFICER")),
                buildDefaultPermissionGroupRequest("Credit_Committee Management", generatePermissionNames("CREDIT_COMMITTEE")),
                buildDefaultPermissionGroupRequest("Individual_Member Management", generatePermissionNames("INDIVIDUAL_MEMBER")),
                buildDefaultPermissionGroupRequest("Group_Representative Management", generatePermissionNames("GROUP_REPRESENTATIVE")),
                buildDefaultPermissionGroupRequest("Group_Member Management", generatePermissionNames("GROUP_MEMBER"))
                );
    }

    private List<String> generatePermissionNames(String prefix){
        List<String> actions = List.of("_READ", "_WRITE", "_CREATE");
        return actions.stream().map(prefix::concat).toList();
    }


}
