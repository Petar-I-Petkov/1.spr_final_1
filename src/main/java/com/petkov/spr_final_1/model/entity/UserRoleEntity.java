package com.petkov.spr_final_1.model.entity;

import com.petkov.spr_final_1.model.entity.enumeration.UserRole;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {

    public UserRoleEntity() {
    }

    public UserRoleEntity(UserRole role) {
        this.role = role;
    }

    private UserRole role;

    @Enumerated(EnumType.STRING)
    public UserRole getRole() {
        return role;
    }

    public UserRoleEntity setRole(UserRole role) {
        this.role = role;
        return this;
    }
}
