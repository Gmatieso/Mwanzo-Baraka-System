package com.gmatieso.mwanzo.security.controller;

import com.gmatieso.mwanzo.common.config.ApiConfig;
import com.gmatieso.mwanzo.security.dtos.UserRequest;
import com.gmatieso.mwanzo.security.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return userService.createUser(userRequest);
    }
}
