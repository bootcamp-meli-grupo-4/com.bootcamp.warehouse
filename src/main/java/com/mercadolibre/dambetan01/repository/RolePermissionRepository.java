package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.model.user.Permission;
import com.mercadolibre.dambetan01.model.user.RolePermission;
import com.mercadolibre.dambetan01.model.user.RolePermissionKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissionKey> {

    @Query("SELECT p.permission FROM RolePermission p WHERE p.rolePermissionKey.roleId = :roleId")
    List<Permission> findPermissionByRoleId(Long roleId);
}
