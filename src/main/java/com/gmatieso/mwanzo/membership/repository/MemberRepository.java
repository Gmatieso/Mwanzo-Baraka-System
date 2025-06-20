package com.gmatieso.mwanzo.membership.repository;

import com.gmatieso.mwanzo.membership.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
//    @EntityGraph(attributePaths = {"share", "contribution", "user"}, type = EntityGraph.EntityGraphType.FETCH)
//    Page<Member> findAll(Pageable pageable);
}
