package cybersoft.javabackend.java18.gira.role.dto;

import cybersoft.javabackend.java18.gira.role.validation.annotation.UniqueRoleCode;
import cybersoft.javabackend.java18.gira.role.validation.annotation.UniqueRoleName;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO { // DTO: Data Transfer Object
    private UUID id;

    @NotBlank
    @UniqueRoleName
    @Length(min = 5, max = 100, message = "{role.name.size}")
    private String name;

    @NotBlank
    @UniqueRoleCode
    @Length(min = 3, max = 10, message = "{role.code.size}")
    private String code;

    @NotBlank(message = "{role.description.blank}")
    private String description;
}
