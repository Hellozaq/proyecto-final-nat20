package org.example.proyectofinalnat20.services;

import jakarta.transaction.Transactional;
import org.example.proyectofinalnat20.entity.Permission;
import org.example.proyectofinalnat20.entity.Role;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleService {

    List<Role> findAll();
    Role findById(Long id);
    Role save(Role role);
    void deleteById(Long id);
    @Transactional
    Role addPermissionToRole(Long roleId, Long permissionId);
    @Transactional
    Role removePermissionFromRole(Long roleId, Long permissionId);
    Set<Permission> getPermissionsForRole(Long roleId);

}
