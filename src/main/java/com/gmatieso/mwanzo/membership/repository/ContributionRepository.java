package com.gmatieso.mwanzo.membership.repository;

import com.gmatieso.mwanzo.membership.entity.Contribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, Long> {
}
