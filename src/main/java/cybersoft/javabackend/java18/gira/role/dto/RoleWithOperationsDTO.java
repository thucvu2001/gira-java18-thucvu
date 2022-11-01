package cybersoft.javabackend.java18.gira.role.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleWithOperationsDTO implements Serializable {
    private UUID id;

    private String name;

    private String code;

    private String description;

    private Set<OperationDTO> operations;
}
