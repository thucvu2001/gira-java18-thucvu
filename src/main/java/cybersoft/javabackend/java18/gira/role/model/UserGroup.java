package cybersoft.javabackend.java18.gira.role.model;

import cybersoft.javabackend.java18.gira.common.model.BaseEntity;
import cybersoft.javabackend.java18.gira.user.model.User;
import cybersoft.javabackend.java18.gira.user.model.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Table(name = RoleEntity.UserGroup.TABLE_NAME)
public class UserGroup extends BaseEntity {

    @Column(name = RoleEntity.UserGroup.NAME, unique = true)
    @Length(min = 5, max = 100, message = "UserGroup name must have length between {min} and {max}")
    private String name;

    @Column(name = RoleEntity.UserGroup.CODE)
    @Length(min = 3, max = 10, message = "UserGroup code must have length between {min} and {max}")
    private String code;

    @Column(name = RoleEntity.UserGroup.DESCRIPTION)
    @NotBlank
    private String description;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = UserEntity.UserMappedUserGroup.JOIN_TABLE,
            joinColumns = @JoinColumn(name = UserEntity.UserMappedUserGroup.JOIN_TABLE_GROUP_ID),
            inverseJoinColumns = @JoinColumn(name = UserEntity.UserMappedUserGroup.JOIN_TABLE_USERS_ID))
    private Set<User> users = new LinkedHashSet<>();

    @ManyToMany(mappedBy = RoleEntity.RoleMappedUserGroup.USER_GROUP_MAPPED_ROLE, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Role> roles = new LinkedHashSet<>();

    public void addUser(User user) {
        this.users.add(user);
        user.getUserGroups().add(this);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
