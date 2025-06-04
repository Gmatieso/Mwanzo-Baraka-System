package com.gmatieso.mwanzo.membership.service;

import com.gmatieso.mwanzo.membership.dtos.ContributionRequest;
import com.gmatieso.mwanzo.membership.entity.Contribution;
import com.gmatieso.mwanzo.membership.mappers.ContributionMapper;
import com.gmatieso.mwanzo.membership.repository.ContributionRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public class ContributionServiceImpl implements ContributionService {

    private final ContributionRepository contributionRepository;
    private final MemberService memberService;
    private final ContributionMapper contributionMapper;

    public ContributionServiceImpl(ContributionRepository contributionRepository, MemberService memberService, ContributionMapper contributionMapper) {
        this.contributionRepository = contributionRepository;
        this.memberService = memberService;
        this.contributionMapper = contributionMapper;
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
//        Contribution contribution = new Contribution();
//        contribution.set
        return null;
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
}
