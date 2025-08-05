package com.gmatieso.mwanzo.membership.repository;

import com.gmatieso.mwanzo.membership.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
