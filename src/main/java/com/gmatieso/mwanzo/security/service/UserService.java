package com.gmatieso.mwanzo.security.service;

import com.gmatieso.mwanzo.security.dtos.UserRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> createUser(UserRequest userRequest);

    ResponseEntity<?> updateUser(Long id, UserRequest userRequest);

    ResponseEntity<?> deleteUser(Long id);

    ResponseEntity<?> getUsers(Pageable pageable);

    ResponseEntity<?> getUser(Long id);

}
