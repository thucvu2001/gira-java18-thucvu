package cybersoft.javabackend.java18.gira.role.model;

import cybersoft.javabackend.java18.gira.common.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Table(name = RoleEntity.Role.TABLE_NAME)
public class Role extends BaseEntity {
    @Column(name = RoleEntity.Role.NAME, unique = true, length = 100)
    @Length(min = 5, max = 100, message = "Role name must have length between {min} and {max}")
    private String name;

    @Column(name = RoleEntity.Role.CODE, unique = true)
    @Length(min = 3, max = 10, message = "Role code must have length between {min} and {max}")
    private String code;

    @Column(name = RoleEntity.Role.DESCRIPTION)
    @NotBlank
    private String description;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = RoleEntity.RoleMappedOperation.JOIN_TABLE,
            joinColumns = @JoinColumn(name = RoleEntity.RoleMappedOperation.JOIN_TABLE_ROLE_ID),
            inverseJoinColumns = @JoinColumn(name = RoleEntity.RoleMappedOperation.JOIN_TABLE_OPERATION_ID))
    private Set<Operation> operations = new LinkedHashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = RoleEntity.RoleMappedUserGroup.JOIN_TABLE,
            joinColumns = @JoinColumn(name = RoleEntity.RoleMappedUserGroup.JOIN_TABLE_ROLE_ID),
            inverseJoinColumns = @JoinColumn(name = RoleEntity.RoleMappedUserGroup.JOIN_TABLE_USER_GROUP_ID))
    private Set<UserGroup> userGroups = new LinkedHashSet<>();

    public void addOperation(Operation operation) {
        this.operations.add(operation);
        operation.getRoles().add(this);
    }

    public void removeOperation(Operation operation) {
        operations.remove(operation);
        operation.getRoles().remove(this);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj)) {
            return false;
        }

        Role role = (Role) obj;

        return this.id != null && Objects.equals(this.id, role.id);
    }
}
