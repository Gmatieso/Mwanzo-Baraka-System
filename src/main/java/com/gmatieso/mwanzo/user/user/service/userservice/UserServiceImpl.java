package com.gmatieso.mwanzo.user.user.service.userservice;

import com.gmatieso.mwanzo.common.exception.BadRequestException;
import com.gmatieso.mwanzo.common.exception.ResourceNotFoundException;
import com.gmatieso.mwanzo.user.user.dtos.UserRequest;
import com.gmatieso.mwanzo.user.user.dtos.UserResponse;
import com.gmatieso.mwanzo.user.user.entity.User;
import com.gmatieso.mwanzo.user.user.mappers.UserMapper;
import com.gmatieso.mwanzo.user.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public UserResponse createUser(UserRequest userRequest) {
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

        return userMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        return null;
    }

    @Override
    public  void deleteUser(Long id) {

    }

    @Override
    public Page<UserResponse> getUsers(Pageable pageable) {
        Page<User> usersPage  = userRepository.findAll(pageable);
        Page<UserResponse> responsePage = usersPage.map(userMapper::toResponse);
        return responsePage;
    }

    @Override
    public UserResponse getUser(Long id) {
        return null;
    }

    @Override
    public User getUserByIdOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User with id" + " " + id + "not found"));
    }
}
