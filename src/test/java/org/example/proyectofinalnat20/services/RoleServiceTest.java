package org.example.proyectofinalnat20.services;

import org.example.proyectofinalnat20.entity.Permission;
import org.example.proyectofinalnat20.entity.RolPermission;
import org.example.proyectofinalnat20.entity.Role;
import org.example.proyectofinalnat20.repository.PermissionRepository;
import org.example.proyectofinalnat20.repository.RoleRepository;
import org.example.proyectofinalnat20.services.implementations.RoleServiceImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PermissionRepository permissionRepository;

    @InjectMocks
    private RoleServiceImplementation roleService;

    @Test
    void getAllRoles_whenExistRoles_returnListOfRoles() {
        //Arrange
        Role role = new Role();
        role.setRoleId(1L);
        role.setName("Test");
        //Act
        when(roleRepository.findAll()).thenReturn(List.of(role));
        //Assert

        List<Role> roles = roleService.findAll();
        assertEquals(1, roles.size());
        assertEquals("Test", roles.get(0).getName());
    }

    @Test
    void getAllRoles_whenNoExistRoles_returnEmptyList() {
        //Arrange
        //Act
        when(roleRepository.findAll()).thenReturn(List.of());
        //Assert
        List<Role> roles = roleService.findAll();
        assertEquals(0, roles.size());
    }

    @Test
    void getRoleById_whenExistRole_returnRole() {
        //Arrange
        Role role = new Role();
        role.setRoleId(1L);
        role.setName("Test");
        //Act
        when(roleRepository.findById(1L)).thenReturn(java.util.Optional.of(role));
        //Assert
        Role role1 = roleService.findById(1L);
        assertEquals("Test", role1.getName());
    }

    @Test
    void getRoleById_whenNoExistRole_throwRuntimeException() {
        //Arrange
        //Act
        when(roleRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        //Assert
        assertThrows(RuntimeException.class, () -> roleService.findById(1L));
    }

    @Test
    void saveRole_whenRoleIsValid_returnRole() {
        //Arrange
        Role role = new Role();
        role.setRoleId(1L);
        role.setName("Test");
        //Act
        when(roleRepository.save(role)).thenReturn(role);
        //Assert
        Role role1 = roleService.save(role);
        assertEquals("Test", role1.getName());
    }

    @Test
    void saveRole_whenExistRole_throwsException() {
        //Arrange
        Role role = new Role();
        role.setRoleId(1L);
        role.setName("Test");
        //Act

        when(roleRepository.findByName(role.getName())).thenReturn(java.util.Optional.of(role));

        assertThrows(RuntimeException.class, () -> roleService.save(role));
    }

    @Test
    void deleteRoleById_whenExistRole_deleteRole() {
        // Arrange
        when(roleRepository.existsById(1L)).thenReturn(true);

        // Act
        roleService.deleteById(1L);

        // Assert
        verify(roleRepository).existsById(1L);
        verify(roleRepository).deleteById(1L);
    }

    @Test
    void deleteRoleById_whenNoExistRole_throwException() {
        // Arrange
        when(roleRepository.existsById(1L)).thenReturn(false);

        // Act
        assertThrows(RuntimeException.class, () -> roleService.deleteById(1L));

        // Assert
        verify(roleRepository).existsById(1L);
    }

    @Test
    void addPermissionToRole_whenExistRoleAndPermission_addPermissionToRole() {
        // Arrange
        Permission permission = new Permission();
        permission.setPermissionId(1L);
        permission.setName("Test");

        Role role = new Role();
        role.setRoleId(1L);
        role.setName("Test");

        when(roleRepository.save(role)).thenReturn(role);
        when(permissionRepository.findById(1L)).thenReturn(java.util.Optional.of(permission));
        when(roleRepository.findById(1L)).thenReturn(java.util.Optional.of(role));

        // Act
        Role permissionRole = roleService.addPermissionToRole(1L, 1L);

        // Assert
        verify(roleRepository).findById(1L);
        verify(permissionRepository).findById(1L);
        verify(roleRepository).save(role);
        assertEquals(1, permissionRole.getRolPermissions().size());
    }

    @Test
    void addPermissionToRole_whenNoExistRole_throwException() {
        // Arrange
        Permission permission = new Permission();
        permission.setPermissionId(1L);
        permission.setName("Test");

        when(roleRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        // Act
        assertThrows(RuntimeException.class, () -> roleService.addPermissionToRole(1L, 1L));

        // Assert
        verify(roleRepository).findById(1L);
    }

    @Test
    void addPermissionToRole_whenNoExistPermission_throwsException(){
        // Arrange
        Role role = new Role();
        role.setRoleId(1L);
        role.setName("Test");

        when(permissionRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        when(roleRepository.findById(1L)).thenReturn(java.util.Optional.of(role));

        // Act
        assertThrows(RuntimeException.class, () -> roleService.addPermissionToRole(1L, 1L));

        // Assert
        verify(roleRepository).findById(1L);
        verify(permissionRepository).findById(1L);
    }

    @Test
    void removePermissionFromRole_whenExistRoleAndPermission_removePermissionFromRole() {
        // Arrange
        Permission permission = new Permission();
        permission.setPermissionId(1L);
        permission.setName("Test");

        Role role = new Role();
        role.setRoleId(1L);
        role.setName("Test");

        RolPermission rolPermission = new RolPermission();
        rolPermission.setPermission(permission);
        rolPermission.setRol(role);

        role.setRolPermissions(new HashSet<>(Collections.singleton(rolPermission)));

        when(roleRepository.save(role)).thenReturn(role);
        when(permissionRepository.findById(1L)).thenReturn(java.util.Optional.of(permission));
        when(roleRepository.findById(1L)).thenReturn(java.util.Optional.of(role));

        // Act
        Role permissionRole = roleService.removePermissionFromRole(1L, 1L);

        // Assert
        verify(roleRepository).findById(1L);
        verify(permissionRepository).findById(1L);
        verify(roleRepository).save(role);
        assertEquals(0, permissionRole.getRolPermissions().size());
    }

    @Test
    void removePermissionFromRole_whenNoExistRole_throwException() {
        // Arrange
        Permission permission = new Permission();
        permission.setPermissionId(1L);
        permission.setName("Test");

        when(roleRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        // Act
        assertThrows(RuntimeException.class, () -> roleService.removePermissionFromRole(1L, 1L));

        // Assert
        verify(roleRepository).findById(1L);
    }

    @Test
    void removePermissionFromRole_whenNoExistPermission_throwsException(){
        //Arrange
        Role role = new Role();
        role.setRoleId(1L);
        role.setName("Test");

        when(permissionRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        when(roleRepository.findById(1L)).thenReturn(java.util.Optional.of(role));

        //Act
        assertThrows(RuntimeException.class, () -> roleService.removePermissionFromRole(1L, 1L));

        //Assert
        verify(roleRepository).findById(1L);
        verify(permissionRepository).findById(1L);
    }

    @Test
    void getPermissionsForRole_whenExistRole_returnPermissions() {
        // Arrange
        Permission permission = new Permission();
        permission.setPermissionId(1L);
        permission.setName("Test");

        Role role = new Role();
        role.setRoleId(1L);
        role.setName("Test");

        RolPermission rolPermission = new RolPermission();
        rolPermission.setPermission(permission);
        rolPermission.setRol(role);

        role.setRolPermissions(Collections.singleton(rolPermission));

        when(roleRepository.findById(1L)).thenReturn(java.util.Optional.of(role));

        // Act
        Set<Permission> permissions = roleService.getPermissionsForRole(1L);

        // Assert
        verify(roleRepository).findById(1L);
        assertEquals(1, permissions.size());
    }

    @Test
    void getPermissionsForRole_whenNoExistRole_throwException() {
        // Arrange
        when(roleRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        // Act
        assertThrows(RuntimeException.class, () -> roleService.getPermissionsForRole(1L));

        // Assert
        verify(roleRepository).findById(1L);
    }

}
