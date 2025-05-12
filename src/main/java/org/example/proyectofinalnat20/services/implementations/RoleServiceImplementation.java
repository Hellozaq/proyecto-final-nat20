package org.example.proyectofinalnat20.services.implementations;

import org.example.proyectofinalnat20.entity.Permission;
import org.example.proyectofinalnat20.entity.Role;
import org.example.proyectofinalnat20.entity.RolPermission;
import org.example.proyectofinalnat20.repository.PermissionRepository;
import org.example.proyectofinalnat20.repository.RolPermissionRepository;
import org.example.proyectofinalnat20.repository.RoleRepository;
import org.example.proyectofinalnat20.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImplementation implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    /**
     * Retrieve all roles
     * @return List of all roles
     */
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    /**
     * Find a role by its ID
     * @param id The ID of the role to find
     * @return The role if found, or an empty Optional
     */
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
    }

    /**
     * Save a new role or update an existing one
     * @param role The role to save
     * @return The saved role
     */
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public Role save(Role role) {
        if (roleRepository.findByName(role.getName()).isPresent()) {
            throw new RuntimeException("Role already exists with name: " + role.getName());
        }
        return roleRepository.save(role);
    }

    /**
     * Delete a role by its ID
     * @param id The ID of the role to delete
     */
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteById(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Role not found with id: " + id);
        }
        roleRepository.deleteById(id);
    }

    /**
     * Add a permission to a role
     * @param roleId The ID of the role
     * @param permissionId The ID of the permission to add
     * @return The updated role
     * @throws RuntimeException if the role or permission is not found
     */
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public Role addPermissionToRole(Long roleId, Long permissionId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + roleId));

        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("Permission not found with id: " + permissionId));

        role.addPermission(permission);
        return roleRepository.save(role);
    }

    /**
     * Remove a permission from a role
     * @param roleId The ID of the role
     * @param permissionId The ID of the permission to remove
     * @return The updated role
     * @throws RuntimeException if the role or permission is not found
     */
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public Role removePermissionFromRole(Long roleId, Long permissionId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + roleId));

        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("Permission not found with id: " + permissionId));

        role.removePermission(permission);
        return roleRepository.save(role);
    }

    /**
     * Get all permissions for a role
     * @param roleId The ID of the role
     * @return Set of permissions associated with the role
     * @throws RuntimeException if the role is not found
     */
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public Set<Permission> getPermissionsForRole(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + roleId));

        return role.getRolPermissions().stream()
                .map(RolPermission::getPermission)
                .collect(Collectors.toSet());
    }
}