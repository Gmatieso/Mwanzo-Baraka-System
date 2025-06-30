package com.gmatieso.mwanzo.membership.service;

import com.gmatieso.mwanzo.common.exception.BadRequestException;
import com.gmatieso.mwanzo.common.exception.ResourceNotFoundException;
import com.gmatieso.mwanzo.common.response.ApiResponseEntity;
import com.gmatieso.mwanzo.common.utils.MemberType;
import com.gmatieso.mwanzo.membership.dtos.MemberRequest;
import com.gmatieso.mwanzo.membership.dtos.MemberResponse;
import com.gmatieso.mwanzo.membership.dtos.MemberResponseBasic;
import com.gmatieso.mwanzo.membership.entity.Member;
import com.gmatieso.mwanzo.membership.mappers.MemberMapper;
import com.gmatieso.mwanzo.membership.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;


    public MemberServiceImpl(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    @Override
    public ResponseEntity<?> createMember(MemberRequest memberRequest) {
        Member member = new Member();
        member.setName(memberRequest.name());
        member.setMemberType(memberRequest.memberType());
        member.setRegistrationDate(memberRequest.registrationDate() != null ? memberRequest.registrationDate() : LocalDateTime.now());
        member.setRegistrationFees(memberRequest.registrationFees());

        Member savedMember = memberRepository.save(member);

        MemberResponseBasic response = memberMapper.toResponseBasic(savedMember);
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
        Member member = getMemberByIdOrThrow(id);
        MemberResponseBasic response = memberMapper.toResponseBasic(member);
        return ApiResponseEntity.success("Member retried successfully", response);
    }

    @Override
    public ResponseEntity<?>  getMembers(Pageable pageable) {
        Page<Member> membersPage = memberRepository.findAll(pageable);

       Page<MemberResponseBasic> responsePage =  membersPage.map(memberMapper::toResponseBasic);

       return ApiResponseEntity.success("Members retrieved successfully", responsePage);
    }

    @Override
    public Member getMemberByIdOrThrow(Long id) {
        return  memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Oops! Sorry ..Member with id"  +  " " + id  + " not found"));
    }

    private void validateRegistrationFees(MemberRequest memberRequest){
        BigDecimal individualFee = new BigDecimal("2000.00");
        BigDecimal groupFee = new BigDecimal("5000.00");
        if (memberRequest.memberType() == MemberType.INDIVIDUAL && memberRequest.registrationFees().compareTo(individualFee) != 0){
            throw new BadRequestException("Registration fee for individual must be Kshs. 2000");
        } else if (memberRequest.memberType() == MemberType.GROUP && memberRequest.registrationFees().compareTo(groupFee) != 0) {
            throw  new BadRequestException("Registration fee for group must be Kshs. 5000");
        }

    }
}
