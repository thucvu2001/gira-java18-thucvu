package cybersoft.javabackend.java18.gira.role.model;

import cybersoft.javabackend.java18.gira.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = RoleEntity.Module.TABLE_NAME)
public class Module extends BaseEntity {

    @Column(name = RoleEntity.Module.NAME)
    @Length(min = 5, max = 100, message = "{module.name.size}")
    private String name;

    @Column(name = RoleEntity.Module.CODE)
    @Length(min = 3, max = 10, message = "{module.code.size}")
    private String code;

    @Column(name = RoleEntity.Module.DESCRIPTION)
    @NotBlank(message = "{module.description.blank}")
    private String description;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
