package cybersoft.javabackend.java18.gira.role.dto;

import cybersoft.javabackend.java18.gira.role.model.Operation;
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
public class OperationDTO {

    private UUID id;

    @NotBlank
    @UniqueRoleName
    @Size(min = 5, max = 100, message = "{service.name.size}")
    private String name;

    @NotBlank
    @UniqueRoleCode
    @Size(min = 3, max = 10, message = "{service.code.size}")
    private String code;

    @NotBlank(message = "{service.description.blank}")
    private String description;

    private Operation.Type type;
}
