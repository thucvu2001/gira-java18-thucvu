package cybersoft.javabackend.java18.gira.common.model;

import cybersoft.javabackend.java18.gira.role.dto.OperationDTO;

import java.util.Set;
import java.util.UUID;

public class RoleWithOperationsDTO {
    private UUID id;

    private String name;

    private String code;

    private String description;

    private Set<OperationDTO> operations;
}
