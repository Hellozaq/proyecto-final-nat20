package org.example.proyectofinalnat20.entity.compositeKeys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Embeddable class that represents the composite primary key for the RolPermission entity.
 * Contains both role and permission identifiers.
 */
@Embeddable
@Data
@NoArgsConstructor
public class RolPermissionId implements Serializable {

    @Column(name = "rol_id")
    private Long rolId;

    @Column(name = "permission_id")
    private Long permissionId;

    public RolPermissionId(Long rolId, Long permissionId) {
        this.rolId = rolId;
        this.permissionId = permissionId;
    }
}
