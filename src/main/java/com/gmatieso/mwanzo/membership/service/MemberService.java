package com.gmatieso.mwanzo.membership.service;

import com.gmatieso.mwanzo.membership.dtos.MemberRequest;
import com.gmatieso.mwanzo.membership.dtos.MemberResponseBasic;
import com.gmatieso.mwanzo.membership.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MemberService {
    MemberResponseBasic createMember(MemberRequest memberRequest);

    MemberResponseBasic updateMember(Long id, MemberRequest memberRequest);

    void deleteMember(Long id);

    MemberResponseBasic getMember(Long id);

    Page<MemberResponseBasic> getMembers(Pageable pageable);

    Member getMemberByIdOrThrow(Long id);
}
