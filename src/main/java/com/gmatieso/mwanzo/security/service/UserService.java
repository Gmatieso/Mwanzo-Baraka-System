package com.gmatieso.mwanzo.security.service;

import com.gmatieso.mwanzo.security.dtos.UserRequest;
import com.gmatieso.mwanzo.security.dtos.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {
   UserResponse createUser(UserRequest userRequest);

   UserResponse updateUser(Long id, UserRequest userRequest);

   void deleteUser(Long id);

   Page<UserResponse> getUsers(Pageable pageable);

    UserResponse getUser(Long id);
}