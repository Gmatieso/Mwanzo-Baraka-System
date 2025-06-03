package com.gmatieso.mwanzo.membership.mappers;

import com.gmatieso.mwanzo.membership.dtos.MemberResponse;
import com.gmatieso.mwanzo.membership.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member toEntity(MemberResponse memberResponse);


    @Mapping(source = "contribution.id", target = "contributionId")
    @Mapping(source = "share.id", target = "shareId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "loan.id", target = "loanId")
    @Mapping(source = "guarantor.id", target = "guarantorId")
    MemberResponse toResponse(Member member);
}
