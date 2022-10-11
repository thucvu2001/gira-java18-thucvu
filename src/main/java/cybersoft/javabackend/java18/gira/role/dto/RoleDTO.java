package cybersoft.javabackend.java18.gira.role.dto;

import cybersoft.javabackend.java18.gira.role.validation.annotation.UniqueRoleCode;
import cybersoft.javabackend.java18.gira.role.validation.annotation.UniqueRoleName;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    @Size(min = 5, max = 100, message = "{role.name.size}")
    private String name;

    @NotBlank
    @UniqueRoleCode
    @Size(min = 3, max = 10, message = "{role.code.size}")
    private String code;

    @NotBlank(message = "{role.description.blank}")
    private String description;
}
