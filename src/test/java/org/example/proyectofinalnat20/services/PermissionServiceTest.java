package org.example.proyectofinalnat20.services;

import org.example.proyectofinalnat20.entity.Permission;
import org.example.proyectofinalnat20.repository.PermissionRepository;
import org.example.proyectofinalnat20.services.implementations.PermissionServiceImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PermissionServiceTest {

    @Mock
    private PermissionRepository permissionRepository;

    @InjectMocks
    private PermissionServiceImplementation permissionService;

    @Test
    void getAllPermissions_whenExistPermissions_returnListOfPermissions() {
        //Arrange
        Permission permission = new Permission();
        permission.setPermissionId(1L);
        permission.setName("Test");
        //Act
        when(permissionRepository.findAll()).thenReturn(List.of(permission));
        //Assert

        List<Permission> permissions = permissionService.findAll();
        assertEquals(1, permissions.size());
        assertEquals("Test", permissions.get(0).getName());
    }

    @Test
    void getAllPermissions_whenNoExistPermissions_returnEmptyList() {
        //Arrange
        //Act
        when(permissionRepository.findAll()).thenReturn(List.of());
        //Assert
        List<Permission> permissions = permissionService.findAll();
        assertEquals(0, permissions.size());
    }

    @Test
    void getPermissionById_whenExistPermission_returnPermission() {
        //Arrange
        Permission permission = new Permission();
        permission.setPermissionId(1L);
        permission.setName("Test");
        //Act
        when(permissionRepository.findById(1L)).thenReturn(java.util.Optional.of(permission));
        //Assert
        Permission permissionResult = permissionService.findById(1L);
        assertEquals("Test", permissionResult.getName());
    }

    @Test
    void getPermissionById_whenNoExistPermission_throwsException() {
        //Arrange
        //Act
        //Assert
        assertThrows(RuntimeException.class, () -> permissionService.findById(1L));

    }

    @Test
    void getPermissionByName_whenExistPermission_returnPermission() {
        //Arrange
        Permission permission = new Permission();
        permission.setPermissionId(1L);
        permission.setName("Test");
        //Act
        when(permissionRepository.findByName("Test")).thenReturn(java.util.Optional.of(permission));
        //Assert
        Permission permissionResult = permissionService.findByName("Test");
        assertEquals("Test", permissionResult.getName());
    }

    @Test
    void getPermissionByName_whenNoExistPermission_throwsException() {
        //Arrange
        //Act
        //Assert
        assertThrows(RuntimeException.class, () -> permissionService.findByName("Test"));

    }

    @Test
    void savePermission_whenSavePermission_returnPermission() {
        //Arrange
        Permission permission = new Permission();
        permission.setPermissionId(1L);
        permission.setName("Test");
        //Act
        when(permissionRepository.save(permission)).thenReturn(permission);
        //Assert
        Permission permissionResult = permissionService.save(permission);
        assertEquals("Test", permissionResult.getName());
    }

    @Test
    void savePermission_whenExistPermission_throwsException() {
        //Arrange
        Permission permission = new Permission();
        permission.setPermissionId(1L);
        permission.setName("Test");
        //Act
        when(permissionRepository.save(permission)).thenThrow(new RuntimeException("Permission already exists"));
        //Assert
        assertThrows(RuntimeException.class, () -> permissionService.save(permission));
    }

    @Test
    void deletePermission_whenExistPermission_deletePermissionSuccessfully() {
        //Arrange
        Permission permission = new Permission();
        permission.setPermissionId(1L);
        permission.setName("Test");
        //Act
        when(permissionRepository.existsById(1L)).thenReturn(true);
        //Assert
        permissionService.deleteById(1L);
    }

    @Test
    void deletePermission_whenNoExistPermission_throwsException() {
        //Arrange
        //Act
        //Assert
        assertThrows(RuntimeException.class, () -> permissionService.deleteById(1L));
    }
}
