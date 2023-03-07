package cybersoft.javabackend.java18.gira.user.model;

import cybersoft.javabackend.java18.gira.common.model.BaseEntity;
import cybersoft.javabackend.java18.gira.role.model.UserGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = UserEntity.User.TABLE_NAME)
@NamedQueries({
        @NamedQuery(name = "User.findByUsernameLikeIgnoreCase", query = "select u from User u where upper(u.username) like upper(:username)"),
        @NamedQuery(name = "User.findByFullName", query = "select u from User u where lower(u.fullName) like lower(:fullname) ")
})
public class User extends BaseEntity {
    @Column(name = UserEntity.User.USERNAME,
            unique = true,
            length = 100,
            updatable = false, // khong duoc sua
            nullable = false)
    @Length(min = 5, max = 100, message = "{user.username.size}")
    private String username;

    @Column(name = UserEntity.User.PASSWORD, nullable = false)
    @Length(min = 5, max = 100, message = "{user.password.size}")
    private String password;

    @Column(name = UserEntity.User.EMAIL,
            unique = true,
            length = 100,
            nullable = false,
            updatable = false)
    @Length(min = 5, max = 100, message = "{user.email.size}")
    private String email;

    @Column(name = UserEntity.User.DISPLAY_NAME)
    @Length(min = 5, max = 30, message = "{user.displayName.size}")
    private String displayName;

    @Column(name = UserEntity.User.FULL_NAME)
    @Length(min = 5, max = 100, message = "{user.fullName.size}")
    private String fullName;

    @Column(name = UserEntity.User.AVATAR)
    private String avatar;

    @Column(name = UserEntity.User.STATUS)
    @Enumerated(EnumType.STRING) // quy dinh kieu cua enum la String
    private Status status;

    @Column(name = UserEntity.User.FACEBOOK_URL)
    private String facebookUrl;

    @Column(name = UserEntity.User.MAJORITY)
    private String majority;

    @Column(name = UserEntity.User.DEPARTMENT)
    private String department;

    @Column(name = UserEntity.User.HOBBIES)
    private String hobbies;

    @ManyToMany(mappedBy = UserEntity.UserMappedUserGroup.USER_GROUP_MAPPED_USER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<UserGroup> userGroups = new LinkedHashSet<>();

    public enum Status {
        ACTIVE,
        TEMPORARY_BLOCKED,
        PERMANENT_BLOCKED
    }
}
