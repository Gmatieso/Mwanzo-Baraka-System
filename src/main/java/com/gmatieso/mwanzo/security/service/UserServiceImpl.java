package com.gmatieso.mwanzo.security.service;

import com.gmatieso.mwanzo.security.dtos.UserRequest;
import com.gmatieso.mwanzo.security.mappers.UserMapper;
import com.gmatieso.mwanzo.security.repository.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserService userService;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserService userService, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @Override
    public ResponseEntity<?> createUser(UserRequest userRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateUser(Long id, UserRequest userRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteUser(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> getUsers(Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<?> getUser(Long id) {
        return null;
    }
}
