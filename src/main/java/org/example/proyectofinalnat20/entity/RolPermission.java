package org.example.proyectofinalnat20.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proyectofinalnat20.entity.compositeKeys.RolPermissionId;

/**
 * Entity that represents the relationship between Roles and Permissions.
 */
@Entity
@Table(name = "roles_permissions")
@Data
public class RolPermission {

    @EmbeddedId
    private RolPermissionId id;

    @ManyToOne
    @MapsId("rolId")
    @JoinColumn(name = "role_id")
    private Role rol;

    @ManyToOne
    @MapsId("permissionId")
    @JoinColumn(name = "permission_id")
    private Permission permission;

    public RolPermission(Role rol, Permission permission) {
        this.rol = rol;
        this.permission = permission;
        this.id = new RolPermissionId(rol.getRoleId(), permission.getPermissionId());
    }

    public RolPermission(){

    }

    public RolPermissionId getId() {
        return id;
    }

    public void setId(RolPermissionId id) {
        this.id = id;
    }

    public Role getRol() {
        return rol;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
