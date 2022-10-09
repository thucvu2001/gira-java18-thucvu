package cybersoft.javabackend.java18.gira.role.model;

import cybersoft.javabackend.java18.gira.common.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Table(name = RoleEntity.Role.TABLE_NAME)
public class Role extends BaseEntity {
    @Column(name = RoleEntity.Role.NAME, unique = true)
    private String name;

    @Column(name = RoleEntity.Role.CODE, unique = true)
    private String code;

    @Column(name = RoleEntity.Role.DESCRIPTION)
    private String description;

    @Override
    public boolean equals(Object obj) {
        Role roleObj = (Role) obj;
        return super.equals(obj)
                && roleObj.name.equals(this.name)
                && roleObj.code.equals(this.code);
    }
}
