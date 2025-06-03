package com.gmatieso.mwanzo.membership.mappers;

import com.gmatieso.mwanzo.membership.dtos.ContributionResponse;
import com.gmatieso.mwanzo.membership.entity.Contribution;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContributionMapper {

   Contribution toEntity(ContributionResponse contributionresponse);

    @Mapping(source = "member.id", target = "memberId")
    ContributionResponse toResponse(Contribution contribution);
}
