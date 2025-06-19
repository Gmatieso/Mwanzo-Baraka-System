package com.gmatieso.mwanzo.membership.mappers;

import com.gmatieso.mwanzo.membership.dtos.ContributionResponse;
import com.gmatieso.mwanzo.membership.dtos.MemberResponse;
import com.gmatieso.mwanzo.membership.dtos.MemberResponseBasic;
import com.gmatieso.mwanzo.membership.dtos.ShareResponse;
import com.gmatieso.mwanzo.membership.entity.Contribution;
import com.gmatieso.mwanzo.membership.entity.Member;
import com.gmatieso.mwanzo.membership.entity.Share;
import org.hibernate.annotations.Source;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member toEntity(MemberResponse memberResponse);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "share", target = "share", qualifiedByName = "mapShare")
    @Mapping(source = "contribution", target = "contribution", qualifiedByName = "mapContributions")
    MemberResponse toResponse(Member member);

    Member toEntity(MemberResponseBasic memberResponseBasic);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "memberType", target = "memberType")
    @Mapping(source = "registrationFees", target = "registrationFees")
    @Mapping(source = "status", target = "status")
    MemberResponseBasic toResponseBasic(Member member);


    @Named("mapContributions")
    default List<ContributionResponse> mapContributionsMember(List<Contribution> contributions) {
        return contributions != null ? contributions.stream()
                .map(contribution -> new ContributionResponse(
                        contribution.getId(),
                        null,
                        contribution.getAmount(),
                        contribution.getGroupShareAmount(),
                        contribution.getIndividualShareAmount(),
                        contribution.getContributionDate()
                ))
                .collect(Collectors.toList()) : Collections.emptyList();
    }


    @Named("mapShare")
    default ShareResponse mapShare(Share share) {
        return share != null ? new ShareResponse(share.getId(), share.getTotalShares(), null,share.getLastUpdated()) : null;
    }
}
