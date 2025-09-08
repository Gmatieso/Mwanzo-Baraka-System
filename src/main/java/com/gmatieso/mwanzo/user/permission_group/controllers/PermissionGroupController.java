package com.gmatieso.mwanzo.user.permission_group.controllers;

import com.gmatieso.mwanzo.common.config.ApiConfig;
import com.gmatieso.mwanzo.user.permission_group.services.PermissionGroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PermissionGroupController.PATH)
@AllArgsConstructor
public class PermissionGroupController {

    private final PermissionGroupService permissionGroupService;

    @GetMapping
    public ResponseEntity<?> getAllPermissionGroups(){
        return permissionGroupService.getAllPermissionGroups();
    }

    public static  final String PATH = ApiConfig.BASE_API_PATH + "permission_groups";
}
