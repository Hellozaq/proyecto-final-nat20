package org.example.proyectofinalnat20.services.implementations;

import org.example.proyectofinalnat20.entity.Permission;
import org.example.proyectofinalnat20.repository.PermissionRepository;
import org.example.proyectofinalnat20.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImplementation implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public Permission findById(Long id) {
        return permissionRepository.findById(id).orElseThrow(() -> new RuntimeException("Permission not found with id: " + id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deleteById(Long id) {
        if (!permissionRepository.existsById(id)){
            throw new RuntimeException("Permission not found with id: " + id);
        }
        permissionRepository.deleteById(id);
    }


    @Override
    public Permission findByName(String name) {
        return permissionRepository.findByName(name).orElseThrow(() -> new RuntimeException("Permission not found with name: " + name));
    }
}
