package com.gmatieso.mwanzo.user.permission_group.repositories;

import com.gmatieso.mwanzo.user.permission_group.models.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PermissionGroupRepository extends JpaRepository<PermissionGroup, UUID> {
    @Query("SELECT pg FROM PermissionGroup pg LEFT JOIN FETCH pg.permissions where pg.name IN :names")
    List<PermissionGroup> findByNameIn(List<String> names);
}
