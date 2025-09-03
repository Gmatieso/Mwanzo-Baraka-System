package com.gmatieso.mwanzo.user.user.controller;

import com.gmatieso.mwanzo.common.config.ApiConfig;
import com.gmatieso.mwanzo.common.response.ApiResponseEntity;
import com.gmatieso.mwanzo.user.user.dtos.UserRequest;
import com.gmatieso.mwanzo.user.user.dtos.UserResponse;
import com.gmatieso.mwanzo.user.user.service.userservice.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(UserController.PATH)
public class UserController {
    public static final String PATH = ApiConfig.BASE_API_PATH + "users";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest){
        UserResponse response = userService.createUser(userRequest);
        return ApiResponseEntity.success("User created successfully", response);
    }

    @GetMapping
    public ResponseEntity<?> getUsers(Pageable pageable){
       Page<UserResponse> response =  userService.getUsers(pageable);
       return ApiResponseEntity.success("Users retrieved successfully", response);
    }
}
