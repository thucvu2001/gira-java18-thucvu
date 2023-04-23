package cybersoft.javabackend.java18.gira.role.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cybersoft.javabackend.java18.gira.common.model.BaseEntity;
import cybersoft.javabackend.java18.gira.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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
@AllArgsConstructor
@SuperBuilder
@Table(name = RoleEntity.Role.TABLE_NAME)
public class Role extends BaseEntity {
    @Column(name = RoleEntity.Role.NAME, unique = true, length = 100)
    @Length(min = 5, max = 100, message = " {role.name.size}")
    private String name;

    @Column(name = RoleEntity.Role.CODE, unique = true)
    @Length(min = 3, max = 10, message = "{role.code.size}")
    private String code;

    @Column(name = RoleEntity.Role.DESCRIPTION)
    @NotBlank(message = "{role.description.blank}")
    private String description; // mo ta

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}) // neu 1 ben bi xoa thi ben con lai van con
    @JoinTable(
            name = RoleEntity.RoleMappedOperation.JOIN_TABLE, // ten bang
            joinColumns = @JoinColumn(name = RoleEntity.RoleMappedOperation.JOIN_TABLE_ROLE_ID), // cot cua chu quan he
            inverseJoinColumns = @JoinColumn(name = RoleEntity.RoleMappedOperation.JOIN_TABLE_OPERATION_ID))
    // cot cua quan he yeu
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
        // khi xoa thi se so sanh operation trong ham va operation trong list operations
        // va so sanh bang ham equals va hashCode
        operations.remove(operation);
        operation.getRoles().remove(this); // this la role hien tai
    }

    public void addUserGroup (UserGroup userGroup) {
        this.userGroups.add(userGroup);
        userGroup.getRoles().add(this);
    }

    public void removeUserGroup (UserGroup userGroup) {
        this.userGroups.remove(userGroup);
        userGroup.getRoles().remove(this);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { // bang nhau ve bộ nhớ (2 object la 1)
            return true;
        }
        if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj)) {
            // bang null hoac la 2 class khac nhau thi return false
            return false;
        }

        // đến đây thi chắc chắn đã khác null và là cùng 1 class
        Role role = (Role) obj; // ep kieu
        return this.id != null && Objects.equals(this.id, role.id);
    }
}
