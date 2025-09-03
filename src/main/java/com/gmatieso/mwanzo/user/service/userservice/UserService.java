package com.gmatieso.mwanzo.user.service.userservice;

import com.gmatieso.mwanzo.user.dtos.UserRequest;
import com.gmatieso.mwanzo.user.dtos.UserResponse;
import com.gmatieso.mwanzo.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
   UserResponse createUser(UserRequest userRequest);

   UserResponse updateUser(Long id, UserRequest userRequest);

   void deleteUser(Long id);

   Page<UserResponse> getUsers(Pageable pageable);

    UserResponse getUser(Long id);

    User getUserByIdOrThrow(Long id);
}