package com.gmatieso.mwanzo.security.service;

import com.gmatieso.mwanzo.common.exception.BadRequestException;
import com.gmatieso.mwanzo.common.response.ApiResponseEntity;
import com.gmatieso.mwanzo.security.dtos.UserRequest;
import com.gmatieso.mwanzo.security.dtos.UserResponse;
import com.gmatieso.mwanzo.security.entity.User;
import com.gmatieso.mwanzo.security.mappers.UserMapper;
import com.gmatieso.mwanzo.security.repository.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public ResponseEntity<?> createUser(UserRequest userRequest) {
        //Check if email or phone already exist
        if (userRepository.existsByEmail(userRequest.email())) {
            throw new BadRequestException("Sorry Email  is already taken!");
        } else if (userRepository.existsByPhone(userRequest.phone())) {
            throw  new BadRequestException("Sorry Phone number already taken!");
        }

        User user = new User();
        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setEmail(userRequest.email());
        user.setPhone(userRequest.phone());
        user.setUsername(userRequest.username());
        user.setPassword(userRequest.password());

        User savedUser = userRepository.save(user);

        UserResponse response = userMapper.toResponse(savedUser);
        return ApiResponseEntity.success("User Created successfully", response);
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
