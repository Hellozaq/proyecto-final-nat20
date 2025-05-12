package org.example.proyectofinalnat20.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a Permission in the security system.
 * Permissions are assigned to roles to determine what actions users can perform.
 */
@Entity
@Table(name = "permissions")
@Data
@NoArgsConstructor
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "permission_id")
    private Long permissionId;

    @Column
    private String name;

    @OneToMany(mappedBy = "permission", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RolPermission> rolPermissions = new HashSet<>();

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<RolPermission> getRolPermissions() {
        return rolPermissions;
    }

    public void setRolPermissions(Set<RolPermission> rolPermissions) {
        this.rolPermissions = rolPermissions;
    }
}
