package com.gmatieso.mwanzo.membership.repository;

import com.gmatieso.mwanzo.membership.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
