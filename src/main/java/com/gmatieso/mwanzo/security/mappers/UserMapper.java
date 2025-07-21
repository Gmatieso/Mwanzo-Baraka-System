package com.gmatieso.mwanzo.security.mappers;

import com.gmatieso.mwanzo.security.dtos.UserResponse;
import com.gmatieso.mwanzo.security.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserResponse userResponse);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phone", target = "phone")
    UserResponse toResponse(User user);
}
