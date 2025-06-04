package com.gmatieso.mwanzo.membership.service;

import com.gmatieso.mwanzo.membership.dtos.MemberRequest;
import com.gmatieso.mwanzo.membership.entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    ResponseEntity<?> createMember(MemberRequest memberRequest);

    ResponseEntity<?> updateMember(Long id, MemberRequest memberRequest);

    ResponseEntity<?> deleteMember(Long id);

    ResponseEntity<?> getMember(Long id);

    ResponseEntity<?> getMembers(Pageable pageable);

    Member getMemberByIdOrThrow(Long id);
}
