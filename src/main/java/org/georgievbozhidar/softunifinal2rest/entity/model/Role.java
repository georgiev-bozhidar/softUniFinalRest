package org.georgievbozhidar.softunifinal2rest.entity.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.georgievbozhidar.softunifinal2rest.entity.enums.Roles;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    @NotNull
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private Roles roles;

    public @NotNull Roles getRoles() {
        return roles;
    }

    public void setRoles(@NotNull Roles roles) {
        this.roles = roles;
    }

    public String getAuthority(){
        return String.valueOf(this.getId());
    }
}
