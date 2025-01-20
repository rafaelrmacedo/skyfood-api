package com.sky.skyfood.domain.repository;

import com.sky.skyfood.domain.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
