package com.gmatieso.mwanzo.security.mappers;

import com.gmatieso.mwanzo.membership.dtos.MemberResponseBasic;
import com.gmatieso.mwanzo.membership.entity.Member;
import com.gmatieso.mwanzo.security.dtos.UserRequest;
import com.gmatieso.mwanzo.security.dtos.UserResponse;
import com.gmatieso.mwanzo.security.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserRequest userRequest);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "members", target = "members", qualifiedByName = "mapMembers")
    UserResponse toResponse(User user);

    @Named("mapMembers")
    default List<MemberResponseBasic> mapMembers(List<Member> members){
        return members != null ? members.stream()
                .map(member -> new MemberResponseBasic(
                        member.getId(),
                        member.getMemberType(),
                        member.getRegistrationFees(),
                        member.getStatus()
                ))
                .collect(Collectors.toList()) : Collections.emptyList();

    }
}
