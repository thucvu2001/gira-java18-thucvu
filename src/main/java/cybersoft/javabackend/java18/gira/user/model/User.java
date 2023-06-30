package cybersoft.javabackend.java18.gira.user.model;

import cybersoft.javabackend.java18.gira.common.model.BaseEntity;
import cybersoft.javabackend.java18.gira.role.model.UserGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

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
public class User extends BaseEntity implements UserDetails, Serializable {
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

    @ManyToMany(mappedBy = "users")
    private Set<UserGroup> userGroups = new LinkedHashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserGroup userGroup : userGroups) {
            userGroup.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(this.username, user.getUsername()) && Objects.equals(this.email, user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.username, this.email);
    }

    public enum Status {
        ACTIVE,
        TEMPORARY_BLOCKED,
        PERMANENT_BLOCKED
    }
}
