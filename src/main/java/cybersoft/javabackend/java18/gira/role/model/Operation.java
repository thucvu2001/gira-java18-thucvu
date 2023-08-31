package cybersoft.javabackend.java18.gira.role.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cybersoft.javabackend.java18.gira.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = RoleEntity.Operation.TABLE_NAME)
public class Operation extends BaseEntity {

    @Column(name = RoleEntity.Operation.NAME)
    @Length(min = 5, max = 100, message = "{operation.name.size}")
    private String name;

    @Column(name = RoleEntity.Operation.CODE)
    @Length(min = 3, max = 10, message = "{operation.code.size}")
    private String code;

    @Column(name = RoleEntity.Operation.DESCRIPTION)
    @NotBlank(message = "{operation.description.blank}")
    private String description;

    @Column(name = RoleEntity.Operation.TYPE, nullable = false) // not null
    @Enumerated(EnumType.STRING) // quy dinh kieu cua enum la String (mac dinh la so) khi luu xuong database
    private Type type;

    @ManyToMany(mappedBy = "operations", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Role> roles = new LinkedHashSet<>();

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    @JoinColumn(name = RoleEntity.ModuleMappedOperation.JOIN_TABLE_MODULE_ID)
    @Fetch(value = FetchMode.SELECT)
    private Module module;

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
