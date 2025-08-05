package com.gmatieso.mwanzo.membership.controller;

import com.gmatieso.mwanzo.common.config.ApiConfig;
import com.gmatieso.mwanzo.common.response.ApiResponseEntity;
import com.gmatieso.mwanzo.membership.dtos.GroupRequest;
import com.gmatieso.mwanzo.membership.dtos.GroupResponseBasic;
import com.gmatieso.mwanzo.membership.service.GroupService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
