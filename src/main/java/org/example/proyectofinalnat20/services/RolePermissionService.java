package org.example.proyectofinalnat20.services;

public interface RolePermissionService {

    // Define the methods that will be implemented in the service class
    void assignPermissionToRole(Long roleId, Long permissionId);

    void removePermissionFromRole(Long roleId, Long permissionId);

    void removeAllPermissionsFromRole(Long roleId);

    boolean hasPermission(Long roleId, Long permissionId);

}
