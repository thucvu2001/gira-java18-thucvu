package cybersoft.javabackend.java18.gira.role.model;

import cybersoft.javabackend.java18.gira.common.model.BaseEntity;
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
@Table(name = RoleEntity.Operation.TABLE_NAME)
public class Operation extends BaseEntity {

    @Column(name = RoleEntity.Operation.NAME)
    @Length(min = 5, max = 100, message = "Operation name must have length between {min} and {max}")
    private String name;

    @Column(name = RoleEntity.Operation.CODE)
    @Length(min = 3, max = 10, message = "Operation code must have length between {min} and {max}")
    private String code;

    @Column(name = RoleEntity.Operation.DESCRIPTION)
    @NotBlank
    private String description;

    @Column(name = RoleEntity.Operation.TYPE, nullable = false)
    @Enumerated(EnumType.STRING) // quy dinh kieu cua enum la String (mac dinh la so)
    private Type type;

    @ManyToMany(mappedBy = RoleEntity.RoleMappedOperation.OPERATION_MAPPED_ROLE)
    private Set<Role> roles = new LinkedHashSet<>();

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public enum Type {
        SAVE_OR_UPDATE,
        FETCH,
        REMOVE
    }
}
