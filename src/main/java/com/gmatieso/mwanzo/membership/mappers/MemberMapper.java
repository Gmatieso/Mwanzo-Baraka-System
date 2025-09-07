package com.gmatieso.mwanzo.membership.mappers;

import com.gmatieso.mwanzo.membership.dtos.*;
import com.gmatieso.mwanzo.membership.entity.Contribution;
import com.gmatieso.mwanzo.membership.entity.Member;
import com.gmatieso.mwanzo.membership.entity.Share;
import com.gmatieso.mwanzo.user.user.dtos.UserResponse;
import com.gmatieso.mwanzo.user.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member toEntity(MemberRequest memberRequest);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "share", target = "share", qualifiedByName = "mapShare")
    @Mapping(source = "contribution", target = "contribution", qualifiedByName = "mapContributions")
    @Mapping(source = "user", target = "user", qualifiedByName = "mapUsers")
    MemberResponse toResponse(Member member);

//    Member toEntity(MemberResponseBasic memberResponseBasic);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "memberTypeEnum", target = "memberTypeEnum")
    @Mapping(source = "registrationFees", target = "registrationFees")
    @Mapping(source = "statusEnum", target = "statusEnum")
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

    @Named("mapUsers")
    default UserResponse mapUsers(User user){
        return user != null ? new UserResponse(user.getId(), user.getFirstName(),user.getLastName(), user.getEmail(),user.getPhone(),user.getUsername()): null;
    }
}
