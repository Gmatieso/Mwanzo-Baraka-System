package com.gmatieso.mwanzo.user.role.controllers;

import com.gmatieso.mwanzo.common.config.ApiConfig;
import com.gmatieso.mwanzo.user.role.dtos.RoleRequest;
import com.gmatieso.mwanzo.user.role.enums.RoleEnum;
import com.gmatieso.mwanzo.user.role.services.RoleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@RestController
@RequestMapping(RoleController.PATH)
@AllArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<?> addRole(@RequestBody @Valid RoleRequest roleRequest){
        return roleService.addRole(roleRequest);
    }

    @GetMapping
    public ResponseEntity<?> getAllRoles(
            @RequestParam(required = false) RoleEnum name,
            Pageable pageable
            ){
        return roleService.getAllRoles(pageable,name);
    }

    @GetMapping("/groups")
    public ResponseEntity<?> getRolesGrouping(){
        return  roleService.getRolesGrouping();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getRoleById(@PathVariable UUID id){
        return roleService.getRoleDetailsById(id);
    }

    @PutMapping("{roleId}")
    public ResponseEntity<?> editRole(@PathVariable UUID roleId, @RequestBody @Valid RoleRequest roleRequest ){
        return roleService.editRole(roleId, roleRequest);
    }

    @DeleteMapping("{roleId}")
    public ResponseEntity<?> deleteRole(@PathVariable UUID roleId){
        return  roleService.deleteRole(roleId);
    }


    public static final String PATH = ApiConfig.BASE_API_PATH + "roles";


}
