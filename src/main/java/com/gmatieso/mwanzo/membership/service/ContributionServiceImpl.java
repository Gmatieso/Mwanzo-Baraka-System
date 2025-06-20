package com.gmatieso.mwanzo.membership.service;

import com.gmatieso.mwanzo.common.exception.ResourceNotFoundException;
import com.gmatieso.mwanzo.common.response.ApiResponseEntity;
import com.gmatieso.mwanzo.common.utils.MemberType;
import com.gmatieso.mwanzo.membership.dtos.ContributionBasicResponse;
import com.gmatieso.mwanzo.membership.dtos.ContributionRequest;
import com.gmatieso.mwanzo.membership.entity.Contribution;
import com.gmatieso.mwanzo.membership.entity.Member;
import com.gmatieso.mwanzo.membership.mappers.ContributionMapper;
import com.gmatieso.mwanzo.membership.repository.ContributionRepository;
import com.gmatieso.mwanzo.membership.repository.MemberRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
@Service
public class ContributionServiceImpl implements ContributionService {

    private final ContributionRepository contributionRepository;
    private final MemberService memberService;
    private final ContributionMapper contributionMapper;
//    private final MemberRepository memberRepository;
    private final MemberServiceImpl memberServiceImpl;

    public ContributionServiceImpl(ContributionRepository contributionRepository, MemberService memberService, ContributionMapper contributionMapper, MemberServiceImpl memberServiceImpl) {
        this.contributionRepository = contributionRepository;
        this.memberService = memberService;
        this.contributionMapper = contributionMapper;
        this.memberServiceImpl = memberServiceImpl;
    }

    @Override
    public ResponseEntity<?> getAllContribution(Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<?> getContributionById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> createContribution(ContributionRequest request) {

        Long memberId = Long.parseLong(request.memberId());

        Member member =   memberServiceImpl.getMemberByIdOrThrow(memberId);

        Contribution contribution = new Contribution();
        contribution.setAmount(request.amount());
        contribution.setContributionDate(request.contributionDate());
        contribution.setGroupShareAmount(request.groupShareAmount());
        contribution.setIndividualShareAmount(request.individualShareAmount());
        contribution.setMember(member);

        Contribution savedContribution = contributionRepository.save(contribution);


        ContributionBasicResponse response = contributionMapper.toBasicResponse(savedContribution);
        return ApiResponseEntity.success("Contribution created successfully",response);

    }

    @Override
    public ResponseEntity<?> updateContribution(Long id, ContributionRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteContribution(Long id) {
        return null;
    }

    @Override
    public Contribution getContributionByIdOrThrow(Long id) {
        return null;
    }

    private void minimumContribution(ContributionRequest request){
        BigDecimal minAmount = new BigDecimal("1000.00");
        if(request.amount().compareTo(minAmount) < 0) {
            throw  new IllegalArgumentException("Contribution amount must be at least Ksh 1000");
        }
    }

    private void validateGroupShareFourGroupMembers(ContributionRequest request, Member member){
//        Member member = new Member();
        if(member.getMemberType() == MemberType.GROUP){
           BigDecimal expectedGroupShare = new BigDecimal("200.00");
           if(request.groupShareAmount() == null || request.groupShareAmount().compareTo(expectedGroupShare) != 0){
               throw new IllegalArgumentException("Group share must be Kshs. 200 for group members");
           }
            BigDecimal minAmount = new BigDecimal("1000.00");
            BigDecimal individualShare = request.amount().subtract(request.groupShareAmount());
            if (individualShare.compareTo(minAmount.subtract(expectedGroupShare)) < 0) {
                throw new IllegalArgumentException("Individual share must be at least Kshs. 800 for group members");
            }
        }else if (request.groupShareAmount() != null) {
            throw new IllegalArgumentException("Group share amount is only applicable for group members");
        }
    }
}
