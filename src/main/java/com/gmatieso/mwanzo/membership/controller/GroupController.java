package com.gmatieso.mwanzo.membership.controller;

import com.gmatieso.mwanzo.common.config.ApiConfig;
import com.gmatieso.mwanzo.common.response.ApiResponseEntity;
import com.gmatieso.mwanzo.membership.dtos.GroupRequest;
import com.gmatieso.mwanzo.membership.dtos.GroupResponseBasic;
import com.gmatieso.mwanzo.membership.service.GroupService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(GroupController.PATH)
public class GroupController {
    public static final String PATH = ApiConfig.BASE_API_PATH + "groups";

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<?> createGroup(@Valid @RequestBody GroupRequest request){
        GroupResponseBasic response = groupService.createGroup(request);
        return ApiResponseEntity.success("Group created successfully", response);
    }

    @GetMapping
    public ResponseEntity<?> getAllGroups(Pageable pageable){
        Page<GroupResponseBasic> responsePage = groupService.getAllGroups(pageable);
        return  ApiResponseEntity.success("Groups retrieved successfully", responsePage);

    }
}
