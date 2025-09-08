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

    public static final String PATH = ApiConfig.BASE_API_PATH + "roles";


}
