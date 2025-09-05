package com.gmatieso.mwanzo.membership.service;

import com.gmatieso.mwanzo.common.exception.BadRequestException;
import com.gmatieso.mwanzo.common.exception.ResourceNotFoundException;
import com.gmatieso.mwanzo.common.utils.MemberTypeEnum;
import com.gmatieso.mwanzo.membership.dtos.MemberRequest;
import com.gmatieso.mwanzo.membership.dtos.MemberResponseBasic;
import com.gmatieso.mwanzo.membership.entity.Member;
import com.gmatieso.mwanzo.membership.mappers.MemberMapper;
import com.gmatieso.mwanzo.membership.repository.MemberRepository;
import com.gmatieso.mwanzo.user.user.entity.User;
import com.gmatieso.mwanzo.user.user.service.userservice.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final UserService userService;


    public MemberServiceImpl(MemberRepository memberRepository, MemberMapper memberMapper, UserService userService) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
        this.userService = userService;
    }

    @Override
    public MemberResponseBasic createMember(MemberRequest memberRequest) {

        Long userId = memberRequest.user_id();
        User user = userService.getUserByIdOrThrow(userId);

        Member member = new Member();
        member.setUser(user);
        member.setMemberTypeEnum(memberRequest.memberTypeEnum());
        member.setRegistrationDate(memberRequest.registrationDate() != null ? memberRequest.registrationDate() : LocalDateTime.now());
        member.setRegistrationFees(memberRequest.registrationFees());

        Member savedMember = memberRepository.save(member);

       return memberMapper.toResponseBasic(savedMember);
    }

    @Override
    public MemberResponseBasic updateMember(Long id, MemberRequest memberRequest) {
          Member member = getMemberByIdOrThrow(id);
          member.setId(memberRequest.user_id());
          member.setRegistrationFees(memberRequest.registrationFees());
          member.setRegistrationDate(memberRequest.registrationDate() != null ? memberRequest.registrationDate(): member.getRegistrationDate());
          member.setMemberTypeEnum(memberRequest.memberTypeEnum());

          Member updatedMember = memberRepository.save(member);

         return memberMapper.toResponseBasic(updatedMember);
    }

    @Override
    public void deleteMember(Long id) {
        Member member = getMemberByIdOrThrow(id);
         memberRepository.delete(member);
    }

    @Override
    public MemberResponseBasic getMember(Long id) {
        Member member = getMemberByIdOrThrow(id);
        return memberMapper.toResponseBasic(member);
    }

    @Override
    public Page<MemberResponseBasic>  getMembers(Pageable pageable) {
        Page<Member> membersPage = memberRepository.findAll(pageable);
        return   membersPage.map(memberMapper::toResponseBasic);
    }

    @Override
    public Member getMemberByIdOrThrow(Long id) {
        return  memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member with id"  +  " " + id  + " not found"));
    }

    private void validateRegistrationFees(MemberRequest memberRequest){
        BigDecimal individualFee = new BigDecimal("2000.00");
        BigDecimal groupFee = new BigDecimal("5000.00");
        if (memberRequest.memberTypeEnum() == MemberTypeEnum.INDIVIDUAL && memberRequest.registrationFees().compareTo(individualFee) != 0){
            throw new BadRequestException("Registration fee for individual must be Kshs. 2000");
        } else if (memberRequest.memberTypeEnum() == MemberTypeEnum.GROUP && memberRequest.registrationFees().compareTo(groupFee) != 0) {
            throw  new BadRequestException("Registration fee for group must be Kshs. 5000");
        }

    }
}
