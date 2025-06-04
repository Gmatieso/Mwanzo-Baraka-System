package com.gmatieso.mwanzo.membership.mappers;

import com.gmatieso.mwanzo.membership.dtos.MemberResponse;
import com.gmatieso.mwanzo.membership.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member toEntity(MemberResponse memberResponse);


    @Mapping(source = "contribution.id", target = "contribution")
    @Mapping(source = "share.id", target = "share")
    MemberResponse toResponse(Member member);
}
