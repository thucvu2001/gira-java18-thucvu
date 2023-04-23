package cybersoft.javabackend.java18.gira.role.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cybersoft.javabackend.java18.gira.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

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

    @OneToMany(mappedBy = "module", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private Set<Operation> operations = new LinkedHashSet<>();

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void addOperation (Operation operation) {
        this.operations.add(operation);
    }

    public void removeOperation (Operation operation) {
        this.operations.remove(operation);
    }
}
