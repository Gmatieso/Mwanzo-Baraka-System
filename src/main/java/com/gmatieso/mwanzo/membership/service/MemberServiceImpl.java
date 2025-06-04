package com.gmatieso.mwanzo.membership.service;

import com.gmatieso.mwanzo.membership.dtos.MemberRequest;
import com.gmatieso.mwanzo.membership.entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public class MemberServiceImpl implements MemberService {
    @Override
    public ResponseEntity<?> createMember(MemberRequest memberRequest) {
        return null;
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
    public ResponseEntity<?> getMembers(Pageable pageable) {
        return null;
    }

    @Override
    public Member getMemberByIdOrThrow(Long id) {
        return null;
    }
}
