package com.gmatieso.mwanzo.membership.service;

import com.gmatieso.mwanzo.common.response.ApiResponseEntity;
import com.gmatieso.mwanzo.membership.dtos.MemberRequest;
import com.gmatieso.mwanzo.membership.dtos.MemberResponse;
import com.gmatieso.mwanzo.membership.entity.Member;
import com.gmatieso.mwanzo.membership.mappers.MemberMapper;
import com.gmatieso.mwanzo.membership.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final ContributionService contributionService;

    public MemberServiceImpl(MemberRepository memberRepository, MemberMapper memberMapper, ContributionService contributionService) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
        this.contributionService = contributionService;
    }

    @Override
    public ResponseEntity<?> createMember(MemberRequest memberRequest) {
        Member member = new Member();
        member.setName(memberRequest.name());
        member.setMemberType(memberRequest.memberType());
        member.setRegistrationDate(memberRequest.registrationDate() != null ? memberRequest.registrationDate() : LocalDateTime.now());
        member.setRegistrationFees(memberRequest.registrationFees());

        Member savedMember = memberRepository.save(member);

        MemberResponse response = memberMapper.toResponse(savedMember);
        return ApiResponseEntity.success("Members created successfully", response);
    }

    @Override
    public ResponseEntity<?> updateMember(Long id, MemberRequest memberRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteMember(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> getMember(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?>                                                                                                                                                                                                                                                                                                                                        getMembers(Pageable pageable) {
        Page<Member> membersPage = memberRepository.findAll(pageable);

       Page<MemberResponse> responsePage =  membersPage.map(memberMapper::toResponse);

       return ApiResponseEntity.success("Members retrieved successfully", responsePage);
    }

    @Override
    public Member getMemberByIdOrThrow(Long id) {
        return null;
    }
}
