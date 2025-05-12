package org.example.proyectofinalnat20.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Represents a Role in the security system.
 * A Role can have multiple users and multiple permissions
 */
@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(length = 100, nullable = false)
    private String name;

    @OneToMany(mappedBy = "role")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RolPermission> rolPermissions = new HashSet<>();

    /**
    * Add a permission to this Role.
    * Creates a new relationship RolPermission and ads it to Rol and Permission.
    *
    * @param permission to add
     */
    public void addPermission(Permission permission) {
        RolPermission rolPermission = new RolPermission(this, permission);
        rolPermissions.add(rolPermission);
        permission.getRolPermissions().add(rolPermission);
    }

    /**
     *Deletes a permission of this Role.
     * Deletes the relationship RolPermission of Rol and Permission
     * @param permission to remove
     */
    public void removePermission(Permission permission) {
        for (Iterator<RolPermission> iterator = rolPermissions.iterator(); iterator.hasNext(); ) {
            RolPermission rolPermission = iterator.next();
            if (rolPermission.getRol().equals(this) && rolPermission.getPermission().equals(permission)) {
                iterator.remove();
                rolPermission.getPermission().getRolPermissions().remove(rolPermission);
                rolPermission.setRol(null);
                rolPermission.setPermission(null);
            }
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role )) return false;
        return roleId != null && roleId.equals(((Role) o).getRoleId());
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<RolPermission> getRolPermissions() {
        return rolPermissions;
    }

    public void setRolPermissions(Set<RolPermission> rolPermissions) {
        this.rolPermissions = rolPermissions;
    }
}
