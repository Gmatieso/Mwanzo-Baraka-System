package com.gmatieso.mwanzo.user.permission.repositories;

import com.gmatieso.mwanzo.user.permission.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID> {
   List<Permission> findByNameIn(List<String> names);
}
