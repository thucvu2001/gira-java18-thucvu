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
public class UserGroupDTO {

    private UUID id;

    @NotBlank
    @UniqueRoleName
    @Size(min = 5, max = 10, message = "{userGroup.name.size}")
    private String name;

    @NotBlank
    @UniqueRoleCode
    @Size(min = 3, max = 10, message = "{userGroup.code.size}")
    private String code;

    @NotBlank(message = "{userGroup.description.blank}")
    private String description;
}
