package com.gmatieso.mwanzo.membership.mappers;

import com.gmatieso.mwanzo.membership.dtos.ContributionBasicResponse;
import com.gmatieso.mwanzo.membership.dtos.ContributionResponse;
import com.gmatieso.mwanzo.membership.dtos.MemberResponse;
import com.gmatieso.mwanzo.membership.entity.Contribution;
import com.gmatieso.mwanzo.membership.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContributionMapper {

   Contribution toEntity(ContributionResponse contributionresponse);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "contributionDate", target = "contributionDate")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "groupShareAmount", target = "groupShareAmount")
    @Mapping(source = "individualShareAmount", target = "individualShareAmount")
    @Mapping(source = "member", target = "members")
    ContributionResponse toResponse(Contribution contribution);

    @Mapping(source = "id", target = "id" )
    @Mapping(source = "contributionDate", target = "contributionDate")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "groupShareAmount", target = "groupShareAmount")
    @Mapping(source = "individualShareAmount", target = "individualShareAmount")
    ContributionBasicResponse toBasicResponse(Contribution contribution);


    @Named("mapMember")
    default MemberResponse mapMember(Member member) {
        return member != null ? new MemberResponse(member.getId(), member.getName(), null, null): null;
    }

}
