package com.gmatieso.mwanzo.membership.mappers;

import com.gmatieso.mwanzo.membership.dtos.*;
import com.gmatieso.mwanzo.membership.entity.Group;
import com.gmatieso.mwanzo.membership.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    Group toEntity(GroupRequest groupRequest);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "registrationNumber", target = "registrationNumber")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "location", target = "location")
    @Mapping(source = "dateRegistered", target = "dateRegistered")
    @Mapping(source = "postalAddress", target = "postalAddress")
    @Mapping(source = "groupCategory", target = "groupCategory")
    @Mapping(source = "sector", target = "sector")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "registrationFee", target = "registrationFee")
    GroupResponse toResponse(Group group);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "registrationNumber", target = "registrationNumber")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "location", target = "location")
    @Mapping(source = "dateRegistered", target = "dateRegistered")
    @Mapping(source = "postalAddress", target = "postalAddress")
    @Mapping(source = "groupCategory", target = "groupCategory")
    @Mapping(source = "sector", target = "sector")
    @Mapping(source = "members", target = "members", qualifiedByName = "mapGroupMembers")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "registrationFee", target = "registrationFee")
    GroupResponseBasic toResponseBasic(Group group);

    @Named("mapGroupMembers")
    default List<MemberResponseBasic> mapGroupMembers(List<Member> memberList){
            return memberList != null ? memberList.stream()
                    .map(member -> new MemberResponseBasic(
                            member.getId(),
                            member.getMemberType(),
                            null,
                            member.getRegistrationFees(),
                            member.getStatus()
                    ))
                    .collect(Collectors.toList()) : Collections.emptyList();
    }
}
